const express = require('express');
const axios = require('axios');
const console = require("node:console");
const router = express.Router();

const SPRING_SERVER_URL = 'http://localhost:8080'; // SpringBoot port



router.get('/movies/:id', async (req, res) => {
  const { id } = req.params;
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/${id}`);
    res.json(response.data);
  } catch (error) {
    console.error('Error movie details:', error.message);
    res.status(500).json({ error: 'Error movie details:' });
  }
});


router.get('/movies/search/:keyword', async (req, res) => {
  const {keyword} = req.params;
  const {page = 0, size = 12} = req.query;
  try {
    const response = await axios.get(`${SPRING_SERVER_URL}/movies/search/${encodeURIComponent(keyword)}`, {
      params: {page, size}
    });
    res.json(response.data);
  } catch (error) {
    console.error('Error searching movie:', error.message);
    res.status(500).json({error: 'Movie search failed'});
  }
})

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

module.exports = router;
