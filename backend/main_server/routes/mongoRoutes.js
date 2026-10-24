/**
 * Mongo Routes Proxy
 *
 * Forwards the requests under /mongo/* to the Express/MongoDB reviews server.
 * Example: GET /mongo/reviews/movie/Barbie -> GET http://localhost:3001/reviews/movie/Barbie
 */
const express = require('express');
const axios = require('axios');
const router = express.Router();

const MONGO_SERVER_URL = process.env.MONGO_SERVER_URL || 'http://localhost:3001';
const REQUEST_TIMEOUT_MS = 10000;

/**
 * @swagger
 * /mongo/reviews/movie/{title}:
 *   get:
 *     summary: Reviews of a movie (filters and pagination)
 *     parameters:
 *       - in: path
 *         name: title
 *         required: true
 *         schema: { type: string }
 *       - in: query
 *         name: page
 *         schema: { type: integer, default: 0 }
 *       - in: query
 *         name: limit
 *         schema: { type: integer, default: 5 }
 *       - in: query
 *         name: top_critic
 *         schema: { type: boolean }
 *       - in: query
 *         name: review_type
 *         schema: { type: string, enum: [Fresh, Rotten] }
 *       - in: query
 *         name: sort_date
 *         schema: { type: string, enum: [asc, desc] }
 *     responses:
 *       200:
 *         description: List of reviews
 *       502:
 *         description: Reviews server unreachable
 *
 * /mongo/reviews/critic/{name}:
 *   get:
 *     summary: Reviews written by a critic
 *     parameters:
 *       - in: path
 *         name: name
 *         required: true
 *         schema: { type: string }
 *     responses:
 *       200:
 *         description: List of reviews
 *
 * /mongo/reviews:
 *   post:
 *     summary: Submit a new review
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *     responses:
 *       201:
 *         description: Review created
 *       400:
 *         description: Invalid review data
 */
router.use('/', async (req, res) => {
  try {
    const hasBody = ['POST', 'PUT', 'PATCH'].includes(req.method);
    const response = await axios({
      method: req.method,
      // req.url is the part after "/mongo", query string included
      url: `${MONGO_SERVER_URL}${req.url}`,
      data: hasBody ? req.body : undefined,
      timeout: REQUEST_TIMEOUT_MS
    });
    res.status(response.status).json(response.data);
  } catch (error) {
    if (error.response) {
      // The reviews server answered with an error: pass it through
      return res.status(error.response.status).json(error.response.data);
    }
    console.error('Error contacting the reviews server:', error.message);
    const status = error.code === 'ECONNABORTED' ? 504 : 502;
    res.status(status).json({ error: 'Reviews server unavailable', detail: error.message });
  }
});

module.exports = router;