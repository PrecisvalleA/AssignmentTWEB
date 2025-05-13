const express = require('express');
const axios = require('axios');
const router = express.Router();

const SPRING_SERVER_URL = 'http://localhost:8080'; // SpringBoot port

//Get all movies
router.get('/movies', async (req, res) => {
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies`);
    res.json(response.data);
  } catch (error) {
    console.error('Spring error:', error.message);
    res.status(500).json({ error: 'Server spring error' });
  }
});

router.get('/movies/details/:id', async (req, res) => {
  const { id } = req.params;
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/details/${id}`);
    res.json(response.data);
  } catch (error) {
    console.error('Errore dettagli film:', error.message);
    res.status(500).json({ error: 'Errore nel recupero dettagli' });
  }
});


module.exports = router;
