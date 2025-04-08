//import mongoose to connect MongoDB
const mongoose = require('mongoose');
const module = require("node:module");
const mongoose = require("mongoose");

const reviewSchema = new mongoose.Schema({
    rotten_tomatoes_link: {type: String, required: true},
    movie_title: {type: String, required: true},
    critic_name: { type: String, required: true },
    top_critic: { type: Boolean, required: true },
    publisher_name: { type: String, required: true },
    review_type: { type: String, enum: ['Fresh', 'Rotten'], required: true },
    review_score: { type: Number, required: false },
    review_date: { type: Date, required: true },
    review_content: { type: String, required: true }
});
module.exports = mongoose.model('Review', reviewSchema);