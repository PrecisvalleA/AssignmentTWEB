const mongoose = require('mongoose');

const reviewSchema = new mongoose.Schema({
    rotten_tomatoes_link: String,
    movie_title: String,
    critic_name: String,
    top_critic: Boolean,
    publisher_name: String,
    review_type: String,
    review_score: String,
    review_date: Date,
    review_content: String
});

module.exports = mongoose.model('Review', reviewSchema);
