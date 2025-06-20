/**
 * Mongo Routes Proxy
 *
 * Forwards requests from /mongo/* to the MongoDB Reviews microservice.
 */

const express = require('express');
const axios = require('axios');
const router = express.Router();

// Proxy all requests under /mongo to the actual MongoDB microservice
router.use('/', async (req, res) => {
  try {
    // Rebuild the full target URL dynamically
    const mongoURL = `http://localhost:3001${req.originalUrl.replace('/mongo', '')}`;

    const response = await axios({
      method: req.method,
      url: mongoURL,
      headers: req.headers,
      data: req.body
    });

    res.status(response.status).json(response.data);
  } catch (error) {
    res.status(error.response?.status || 500).json({
      message: 'Server error MongoDB',
      error: error.message
    });
  }
});

module.exports = router;
