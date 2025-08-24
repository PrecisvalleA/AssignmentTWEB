/**
 * Spring Routes Proxy
 *
 * Forwards specific requests under /spring/* to the Spring Boot backend server.
 */
const express = require('express');
const axios = require('axios');
const console = require("node:console");
const router = express.Router();

const SPRING_SERVER_URL = 'http://localhost:8080'; // SpringBoot port

// Paginated movie filtering with multiple query parameters
/**
 * @swagger
 * /movies/paginated:
 *   get:
 *     summary: Paginated movie filtering with multiple query parameters
 *     parameters:
 *       - in: query
 *         name: page
 *         schema:
 *           type: integer
 *         description: Page number
 *       - in: query
 *         name: size
 *         schema:
 *           type: integer
 *         description: Page size
 *       - in: query
 *         name: [other possible filters]
 *         schema:
 *           type: string
 *         description: Other possible filtering parameters
 *     responses:
 *       200:
 *         description: Paginated list of movies
 *       500:
 *         description: Error fetching paginated movies
 */
router.get('/movies/paginated', async (req, res) => {
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/paginated`, {
      params: req.query
    });
    res.json(response.data);
  } catch (error) {
    console.error('Error fetching paginated movies:', error.message);
    res.status(500).json({ error: 'Error fetching paginated movies' });
  }
})


// Search movies by keyword
/**
 * @swagger
 * /movies/search/{keyword}:
 *   get:
 *     summary: Search movies by keyword
 *     parameters:
 *       - in: path
 *         name: keyword
 *         required: true
 *         schema:
 *           type: string
 *         description: Keyword to search movies
 *       - in: query
 *         name: page
 *         schema:
 *           type: integer
 *           default: 0
 *         description: Page number
 *       - in: query
 *         name: size
 *         schema:
 *           type: integer
 *           default: 12
 *         description: Page size
 *     responses:
 *       200:
 *         description: Search results
 *       500:
 *         description: Error searching movies
 */
router.get('/movies/search/:keyword', async (req, res) => {

  try {
    const {keyword} = req.params;
    const {page = 0, size = 12} = req.query;
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/search/${encodeURIComponent(keyword)}`, {
      params: {page, size}
    });
    res.status(response.status).json(response.data);
  } catch (error) {
    console.error('Error search → status:', error.response?.status);
    console.error('Error search → body  :', error.response?.data);
    res.status(status).json(body);
  }
})

// Get movie details by ID
/**
 * @swagger
 * /movies/{id}:
 *   get:
 *     summary: Get movie details by ID
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Movie ID
 *     responses:
 *       200:
 *         description: Movie details
 *       500:
 *         description: Error fetching movie details
 */
router.get('/movies/:id(\\d+)', async (req, res) => {
  const { id } = req.params;
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/${id}`);
    res.json(response.data);
  } catch (error) {
    console.error('Error movie details:', error.message);
    res.status(500).json({ error: 'Error movie details:' });
  }
});

module.exports = router;