const express = require('express');
const axios = require('axios');
const router = express.Router();

// Inoltra tutte le richieste a /mongo/* al server MongoDB
router.use('/', async (req, res) => {
  try {
    const mongoURL = `http://localhost:3000${req.originalUrl.replace('/mongo', '')}`;

    const response = await axios({
      method: req.method,
      url: mongoURL,
      headers: req.headers,
      data: req.body
    });

    res.status(response.status).json(response.data);
  } catch (error) {
    res.status(error.response?.status || 500).json({
      message: 'Errore nel proxy verso il server MongoDB',
      error: error.message
    });
  }
});

module.exports = router;
