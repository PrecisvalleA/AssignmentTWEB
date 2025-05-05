const express = require('express');
const axios = require('axios');
const router = express.Router();

const SPRING_SERVER_URL = 'http://localhost:8080'; // porta di Spring Boot

// Inoltra tutte le richieste a /spring/* verso il server Spring Boot con la rotta corretta
router.use('/', async (req, res) => {
  try {
    const springUrl = SPRING_SERVER_URL + req.originalUrl.replace('/spring', '');

    const response = await axios({
      method: req.method,
      url: springUrl,
      headers: req.headers,
      data: req.body
    });

    res.status(response.status).json(response.data);
  } catch (error) {
    res.status(error.response?.status || 500).json({
      message: 'Errore nel proxy verso Spring Boot',
      error: error.message
    });
  }
});

module.exports = router;
