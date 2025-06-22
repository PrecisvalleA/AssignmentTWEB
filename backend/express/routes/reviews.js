/**
 * Reviews API Routes
 * This Express router manages all endpoints related to movie reviews.
 */
/**
 * @swagger
 * components:
 *   schemas:
 *     Review:
 *       type: object
 *       required:
 *         - movie_title
 *         - critic_name
 *         - review_date
 *         - review_type
 *         - top_critic
 *         - score
 *         - review_text
 *       properties:
 *         _id:
 *           type: string
 *           description: The auto-generated id of the review
 *         movie_title:
 *           type: string
 *         critic_name:
 *           type: string
 *         review_date:
 *           type: string
 *           format: date
 *         review_type:
 *           type: string
 *         top_critic:
 *           type: boolean
 *         score:
 *           type: number
 *         review_text:
 *           type: string
 */
const express = require('express');
const router = express.Router();
const Reviews = require('../models/Reviews');
const req = require("express/lib/request");


// GET /reviews/
// Retrieve all reviews from MongoDB
/**
 * @swagger
 * /reviews/:
 *   get:
 *     summary: Retrieve all reviews
 *     responses:
 *       200:
 *         description: List of reviews
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Review'
 */
router.get('/', async (req, res) => {
  try {
    const reviews = await Reviews.find();
    res.json(reviews);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// GET /reviews/movie/:title
// Retrieve reviews by movie title, with optional filters and pagination
/**
 * @swagger
 * /reviews/movie/{title}:
 *   get:
 *     summary: Retrieve reviews by movie title with filters
 *     parameters:
 *       - in: path
 *         name: title
 *         required: true
 *         schema:
 *           type: string
 *         description: Movie title
 *       - in: query
 *         name: page
 *         schema:
 *           type: integer
 *       - in: query
 *         name: limit
 *         schema:
 *           type: integer
 *       - in: query
 *         name: top_critic
 *         schema:
 *           type: boolean
 *       - in: query
 *         name: review_type
 *         schema:
 *           type: string
 *       - in: query
 *         name: sort_date
 *         schema:
 *           type: string
 *           enum: [asc, desc]
 *     responses:
 *       200:
 *         description: List of filtered reviews
 */
router.get('/movie/:title', async (req, res) => {
    try {
        const title = decodeURIComponent(req.params.title);
        const page = parseInt(req.query.page) || 0;
        const limit = parseInt(req.query.limit) || 5;

        // Building query filters dynamically
        const filters = {movie_title: new RegExp(req.params.title, 'i')};

        if (req.query.top_critic) {
            filters.top_critic = req.query.top_critic === 'true';
        }


        if(req.query.review_type){
            filters.review_type = req.query.review_type;
        }

        let sort={};

        if(req.query.sort_date === 'desc'){
            sort.review_date = -1;
        }else if(req.query.sort_date === 'asc'){
            sort.review_date = 1;
        }

        const reviews = await Reviews.find(filters)
                                                .sort(sort)
                                                .skip(page*limit)
                                                .limit(limit);
        res.json(reviews);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
})


// GET /reviews/critic/:name
// Retrieve reviews written by a specific critic
/**
 * @swagger
 * /reviews/critic/{name}:
 *   get:
 *     summary: Retrieve reviews by critic name
 *     parameters:
 *       - in: path
 *         name: name
 *         required: true
 *         schema:
 *           type: string
 *     responses:
 *       200:
 *         description: List of reviews by critic
 */
router.get('/critic/:name', async (req, res) => {
    try {
        const reviews = await Reviews.find({critic_name: new RegExp(req.params.name, 'i')});
        res.json(reviews);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
})

// POST /reviews/
// Create a new review
/**
 * @swagger
 * /reviews/:
 *   post:
 *     summary: Create a new review
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/Review'
 *     responses:
 *       201:
 *         description: Review created
 */
router.post('/', async (req, res) => {
    try {
        const review = new Reviews(req.body);
        await review.save();
        res.status(201).json(review);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
})

module.exports = router;