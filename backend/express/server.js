const express = require('express');
const mongoose = require('mongoose');
const app = express();

const PORT = 3000;
const MONGO_URI = 'mongodb://localhost:27017/movieDB'; // Change database name

// Connection to MongoDB
mongoose.connect(MONGO_URI)
    .then(() => console.log('Connected to MongoDB'))
    .catch(err => console.error('Error connecting to MongoDB:', err));

// Endpoint di base
app.get('/', (req, res) => {
  res.send('Welcome to the central server!');
});

// Avvio del server
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
