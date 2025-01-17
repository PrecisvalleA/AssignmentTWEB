const express = require('express');
const mongoose = require('mongoose');
const app = express();
const Movie = require('./models/Movie');


const PORT = 3000;
const MONGO_URI = 'mongodb://localhost:27017/movieDB'; // Change database name

// Connection to MongoDB
mongoose.connect(MONGO_URI)
    .then(() => console.log('Connected to MongoDB'))
    .catch(err => console.error('Error connecting to MongoDB:', err));

app.use(express.json());

// Endpoint
app.get('/', (req, res) => {
  res.send('Welcome to the central server!');
});

// CREATE: Add a new movie
app.post('/movies', async (req, res) => {
  try {
    const movie = new Movie(req.body);
    await movie.save();
    res.status(201).send(movie);
  } catch (err) {
    res.status(400).send(err.message);
  }
});

// READ: get all movies
app.get('/movies', async (req, res) => {
  try {
    const movies = await Movie.find();
    res.send(movies);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

// UPDATE: update movie
app.put('/movies/:id', async (req, res) => {
  try {
    const movie = await Movie.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!movie) return res.status(404).send('Movie not found');
    res.send(movie);
  } catch (err) {
    res.status(400).send(err.message);
  }
});

// DELETE: delete movie
app.delete('/movies/:id', async (req, res) => {
  try {
    const movie = await Movie.findByIdAndDelete(req.params.id);
    if (!movie) return res.status(404).send('Movie not found');
    res.send(movie);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

// server start
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
