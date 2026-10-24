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
    movie_title: { type: String, required: true },
    /** Name of the critic who wrote the review */
    critic_name: { type: String, required: true },
    /** Flag indicating if the critic is a "top critic" */
    top_critic: { type: Boolean, default: false },
    /** Publisher of the review */
    publisher_name: String,
    /** Type of the review (Fresh or Rotten) */
    review_type: { type: String, enum: ['Fresh', 'Rotten'] },
    /** Score assigned to the review */
    review_score: String,
    /** Date when the review was published */
    review_date: { type: Date, default: Date.now },
    /** Full review content text */
    review_content: { type: String, required: true }
});

// Index used by GET /reviews/movie/:title (case-insensitive exact match + sort by date)
reviewSchema.index(
    { movie_title: 1, review_date: -1 },
    { collation: { locale: 'en', strength: 2 } }
);

// Export the model for use in the API
module.exports = mongoose.model('Review', reviewSchema);