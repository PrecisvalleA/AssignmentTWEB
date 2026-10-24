/**
 * Spring Routes Proxy
 *
 * Forwards the requests under /spring/* to the Spring Boot backend server.
 * Status codes returned by Spring are passed through to the client;
 * 502 / 504 are returned when Spring Boot is unreachable / too slow.
 */
const express = require('express');
const axios = require('axios');
const router = express.Router();

const SPRING_SERVER_URL = process.env.SPRING_URL || 'http://localhost:8080';
const REQUEST_TIMEOUT_MS = 10000;
const DETAILS_TIMEOUT_MS = 60000;

/** Sends to the client the error received from Spring (or a 502/504 if Spring did not answer). */
function sendError(res, error, context) {
  if (error.response) {
    return res.status(error.response.status).json(error.response.data || { error: context });
  }
  console.error(`${context}:`, error.message);
  const status = error.code === 'ECONNABORTED' ? 504 : 502;
  return res.status(status).json({ error: context, detail: error.message });
}

// Paginated movie filtering with multiple query parameters
/**
 * @swagger
 * /spring/movies/paginated:
 *   get:
 *     summary: Paginated and filtered list of movies
 *     parameters:
 *       - in: query
 *         name: page
 *         schema: { type: integer, default: 0 }
 *       - in: query
 *         name: size
 *         schema: { type: integer, default: 12 }
 *       - in: query
 *         name: sort
 *         schema: { type: string, example: "rating,desc" }
 *         description: Property and direction, e.g. rating,desc or name,asc
 *       - in: query
 *         name: minRating
 *         schema: { type: number }
 *       - in: query
 *         name: maxRating
 *         schema: { type: number }
 *       - in: query
 *         name: minDate
 *         schema: { type: string, example: "1990" }
 *       - in: query
 *         name: maxDate
 *         schema: { type: string, example: "2020" }
 *       - in: query
 *         name: minDuration
 *         schema: { type: number }
 *       - in: query
 *         name: maxDuration
 *         schema: { type: number }
 *       - in: query
 *         name: genre
 *         schema: { type: string, example: "Drama" }
 *     responses:
 *       200:
 *         description: Page of movies (movie, posterUrl, genres)
 *       502:
 *         description: Spring Boot server unreachable
 *       504:
 *         description: Spring Boot server timeout
 */
router.get('/movies/paginated', async (req, res) => {
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/paginated`, {
      params: req.query,
      timeout: REQUEST_TIMEOUT_MS
    });
    res.json(response.data);
  } catch (error) {
    sendError(res, error, 'Error fetching paginated movies');
  }
});

// Search movies by keyword
/**
 * @swagger
 * /spring/movies/search/{keyword}:
 *   get:
 *     summary: Search movies by keyword in the title
 *     parameters:
 *       - in: path
 *         name: keyword
 *         required: true
 *         schema: { type: string }
 *       - in: query
 *         name: page
 *         schema: { type: integer, default: 0 }
 *       - in: query
 *         name: size
 *         schema: { type: integer, default: 12 }
 *     responses:
 *       200:
 *         description: Page of search results
 *       502:
 *         description: Spring Boot server unreachable
 *       504:
 *         description: Spring Boot server timeout
 */
router.get('/movies/search/:keyword', async (req, res) => {
  try {
    const { page = 0, size = 12 } = req.query;
    const response = await axios.get(
        `${SPRING_SERVER_URL}/movies/search/${encodeURIComponent(req.params.keyword)}`,
        { params: { page, size }, timeout: REQUEST_TIMEOUT_MS }
    );
    res.json(response.data);
  } catch (error) {
    sendError(res, error, 'Error searching movies');
  }
});

// Get movie details by ID
/**
 * @swagger
 * /spring/movies/{id}:
 *   get:
 *     summary: Get the full details of a movie
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema: { type: integer }
 *     responses:
 *       200:
 *         description: Movie details (cast, crew, genres, releases, oscars, ...)
 *       404:
 *         description: Movie not found
 *       502:
 *         description: Spring Boot server unreachable
 *       504:
 *         description: Spring Boot server timeout
 */
router.get('/movies/:id(\\d+)', async (req, res) => {
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/${req.params.id}`, {
      timeout: DETAILS_TIMEOUT_MS
    });
    res.json(response.data);
  } catch (error) {
    sendError(res, error, 'Error fetching movie details');
  }
});

module.exports = router;