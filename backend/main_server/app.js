const express = require('express');
const cors = require('cors');
const springRoutes = require('./routes/springRoutes');
const mongoRoutes = require('./routes/mongoRoutes');

const app = express();

const swaggerOptions = {
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'Movie & Mongo Proxy API',
            version: '1.0.0',
        },
    },
    apis: ['./routes/springRoutes' , './routes/mongoRoutes'],
};

app.use(cors());
app.use(express.json());

app.use('/spring', springRoutes);
app.use('/mongo', mongoRoutes);

module.exports = app;