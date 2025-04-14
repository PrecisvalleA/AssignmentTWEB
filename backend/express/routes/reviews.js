const express = require('express');
const router = express.Router();
const Reviews = require('../models/Reviews');

//get all reviews
router.get('/', async (req, res) => {
  try {
    const reviews = await Reviews.find();
    res.json(reviews);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

//get reviews by movie title
router.get('/movie/:title', async (req, res) => {
    try {
        const reviews = await Reviews.find({movie_title: new RegExp(req.params.title, 'i')});
        res.json(reviews);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
})

//get critic's reviews by name
router.get('/critic/:name', async (req, res) => {
    try {
        const reviews = await Reviews.find({critic_name: new RegExp(req.params.name, 'i')});
        res.json(reviews);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
})

//post new review
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