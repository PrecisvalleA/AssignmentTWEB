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
 *         - review_content
 *       properties:
 *         _id:
 *           type: string
 *           description: The auto-generated id of the review
 *         rotten_tomatoes_link:
 *           type: string
 *         movie_title:
 *           type: string
 *         critic_name:
 *           type: string
 *         top_critic:
 *           type: boolean
 *         publisher_name:
 *           type: string
 *         review_type:
 *           type: string
 *           enum: [Fresh, Rotten]
 *         review_score:
 *           type: string
 *         review_date:
 *           type: string
 *           format: date-time
 *         review_content:
 *           type: string
 */
const express = require('express');
const router = express.Router();
const Reviews = require('../models/Reviews');

const MAX_LIMIT = 50;
const REVIEW_TYPES = ['Fresh', 'Rotten'];
// Case-insensitive exact match on the title (uses the index defined in models/Reviews.js)
const TITLE_COLLATION = { locale: 'en', strength: 2 };

/** Escapes regex special characters so user input is matched literally. */
function escapeRegex(text) {
    return String(text).replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}

/** Reads and sanitizes the page/limit query parameters. */
function parsePagination(query) {
    const page = Math.max(parseInt(query.page, 10) || 0, 0);
    const limit = Math.min(Math.max(parseInt(query.limit, 10) || 5, 1), MAX_LIMIT);
    return { page, limit };
}

// GET /reviews/
/**
 * @swagger
 * /reviews/:
 *   get:
 *     summary: Retrieve a page of reviews
 *     parameters:
 *       - in: query
 *         name: page
 *         schema:
 *           type: integer
 *           default: 0
 *       - in: query
 *         name: limit
 *         schema:
 *           type: integer
 *           default: 5
 *           maximum: 50
 *     responses:
 *       200:
 *         description: Page of reviews with the total number of reviews
 *       500:
 *         description: Server error
 */
router.get('/', async (req, res) => {
    try {
        const { page, limit } = parsePagination(req.query);
        const [reviews, total] = await Promise.all([
            Reviews.find().sort({ _id: 1 }).skip(page * limit).limit(limit),
            Reviews.estimatedDocumentCount()
        ]);
        res.json({ page, limit, total, reviews });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// GET /reviews/movie/:title
/**
 * @swagger
 * /reviews/movie/{title}:
 *   get:
 *     summary: Retrieve reviews by exact movie title (case-insensitive) with filters
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
 *           default: 0
 *       - in: query
 *         name: limit
 *         schema:
 *           type: integer
 *           default: 5
 *           maximum: 50
 *       - in: query
 *         name: top_critic
 *         schema:
 *           type: boolean
 *       - in: query
 *         name: review_type
 *         schema:
 *           type: string
 *           enum: [Fresh, Rotten]
 *       - in: query
 *         name: sort_date
 *         schema:
 *           type: string
 *           enum: [asc, desc]
 *     responses:
 *       200:
 *         description: List of filtered reviews
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Review'
 *       500:
 *         description: Server error
 */
router.get('/movie/:title', async (req, res) => {
    try {
        const { page, limit } = parsePagination(req.query);
        const filters = { movie_title: req.params.title };

        if (req.query.top_critic === 'true' || req.query.top_critic === 'false') {
            filters.top_critic = req.query.top_critic === 'true';
        }
        if (REVIEW_TYPES.includes(req.query.review_type)) {
            filters.review_type = req.query.review_type;
        }

        // _id as last criterion makes the pagination deterministic
        const sort = {};
        if (req.query.sort_date === 'desc') sort.review_date = -1;
        else if (req.query.sort_date === 'asc') sort.review_date = 1;
        sort._id = 1;

        const reviews = await Reviews.find(filters)
            .collation(TITLE_COLLATION)
            .sort(sort)
            .skip(page * limit)
            .limit(limit);
        res.json(reviews);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// GET /reviews/critic/:name
/**
 * @swagger
 * /reviews/critic/{name}:
 *   get:
 *     summary: Retrieve reviews by critic name (partial, case-insensitive match)
 *     parameters:
 *       - in: path
 *         name: name
 *         required: true
 *         schema:
 *           type: string
 *       - in: query
 *         name: page
 *         schema:
 *           type: integer
 *           default: 0
 *       - in: query
 *         name: limit
 *         schema:
 *           type: integer
 *           default: 5
 *           maximum: 50
 *     responses:
 *       200:
 *         description: List of reviews by critic
 *       500:
 *         description: Server error
 */
router.get('/critic/:name', async (req, res) => {
    try {
        const { page, limit } = parsePagination(req.query);
        const reviews = await Reviews.find({ critic_name: new RegExp(escapeRegex(req.params.name), 'i') })
            .sort({ _id: 1 })
            .skip(page * limit)
            .limit(limit);
        res.json(reviews);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// POST /reviews/
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
 *       400:
 *         description: Invalid review data
 */
router.post('/', async (req, res) => {
    try {
        const {
            rotten_tomatoes_link, movie_title, critic_name, top_critic,
            publisher_name, review_type, review_score, review_date, review_content
        } = req.body || {};

        if (!movie_title || !critic_name || !review_content) {
            return res.status(400).json({ message: 'movie_title, critic_name and review_content are required' });
        }
        if (review_type !== undefined && !REVIEW_TYPES.includes(review_type)) {
            return res.status(400).json({ message: 'review_type must be Fresh or Rotten' });
        }

        const review = new Reviews({
            rotten_tomatoes_link, movie_title, critic_name, top_critic,
            publisher_name, review_type, review_score, review_date, review_content
        });
        await review.save();
        res.status(201).json(review);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
});

module.exports = router;