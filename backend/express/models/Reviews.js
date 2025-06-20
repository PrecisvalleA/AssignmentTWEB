/**
 * Mongoose Schema definition for Reviews collection.
 *
 * Each document represents a single movie review entry.
 */
const mongoose = require('mongoose');

const reviewSchema = new mongoose.Schema({
    /** Rotten Tomatoes review link */
    rotten_tomatoes_link: String,
    /** Title of the movie being reviewed */
    movie_title: String,
    /** Name of the critic who wrote the review */
    critic_name: String,
    /** Flag indicating if the critic is a "top critic" */
    top_critic: Boolean,
    /** Publisher of the review */
    publisher_name: String,
    /** Type of the review */
    review_type: String,
    /** Score assigned to the review */
    review_score: String,
    /** Date when the review was published */
    review_date: Date,
    /** Full review content text */
    review_content: String
});
// Export the model for use in the API
module.exports = mongoose.model('Review', reviewSchema);
