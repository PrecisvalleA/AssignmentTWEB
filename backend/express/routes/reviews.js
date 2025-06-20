/**
 * Reviews API Routes
 * This Express router manages all endpoints related to movie reviews.
 */

const express = require('express');
const router = express.Router();
const Reviews = require('../models/Reviews');
const req = require("express/lib/request");

// GET /reviews/
// Retrieve all reviews from MongoDB
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