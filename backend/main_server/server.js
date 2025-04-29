const express = require('express');
const cors = require('cors');
const springRoutes = require('./routes/springRoutes');
const mongoRoutes = require('./routes/mongoRoutes');

const app = express();

app.use(cors());
app.use(express.json());

app.use('/spring', springRoutes);
app.use('/mongo', mongoRoutes);

module.exports = app; // ✅ export app
