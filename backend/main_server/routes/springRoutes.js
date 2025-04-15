const express = require('express');
const axios = require('axios');
const router = express.Router();

const SPRING_SERVER_URL = 'http://localhost:8080'; // porta di Spring Boot

router.get('/films', async (req, res) => {
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/films`);
    res.json(response.data);
  } catch (error) {
    console.error('Errore Spring:', error.message);
    res.status(500).json({ error: 'Errore dal server Spring' });
  }
});

module.exports = router;
