// server.js
const express = require('express');
const cors = require('cors');
const springRoutes = require('./routes/springRoutes');
const mongoRoutes = require('./routes/mongoRoutes');

const app = express();
const PORT = process.env.PORT || 5001; // Porta del main server

app.use(cors());
app.use(express.json());

// Smista le richieste verso i due server interni
app.use('/spring', springRoutes);
app.use('/mongo', mongoRoutes);

// Esporta l'app per essere utilizzata da `www`
module.exports = app; // ✅ Export dell'istanza di Express
