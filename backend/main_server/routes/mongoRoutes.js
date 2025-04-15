const express = require('express');
const axios = require('axios');
const router = express.Router();

const MONGO_SERVER_URL = 'http://localhost:3000'; // porta del server Express MongoDB

router.get('/reviews', async (req, res) => {
  try {
    const response = await axios.get(`${MONGO_SERVER_URL}/reviews`);
    res.json(response.data);
  } catch (error) {
    console.error('Errore Mongo:', error.message);
    res.status(500).json({ error: 'Errore dal server MongoDB' });
  }
});

module.exports = router;
