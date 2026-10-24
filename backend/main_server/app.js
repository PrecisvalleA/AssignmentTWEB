/**
 * Main server (API gateway).
 *
 * It does not perform any heavy work: it only forwards the requests of the
 * frontend to the Spring Boot server (/spring/*) and to the Express/MongoDB
 * reviews server (/mongo/*). The real-time chat (Socket.IO) is attached to
 * the HTTP server in bin/www (see socketHandlers.js).
 */
const path = require('path');
const express = require('express');
const cors = require('cors');
const swaggerUi = require('swagger-ui-express');
const swaggerJsdoc = require('swagger-jsdoc');
const springRoutes = require('./routes/springRoutes');
const mongoRoutes = require('./routes/mongoRoutes');

const app = express();

const swaggerSpec = swaggerJsdoc({
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'FrameVerse API Gateway',
            version: '1.0.0',
            description: 'Gateway that forwards requests to the Spring Boot server (movies) and to the ' +
                'Express/MongoDB server (reviews). The real-time chat runs on the Socket.IO namespace /chat.'
        },
        servers: [{ url: 'http://localhost:3000' }]
    },
    apis: [path.join(__dirname, 'routes', '*.js')]
});

app.use(cors());
app.use(express.json());

app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));
app.use('/spring', springRoutes);
app.use('/mongo', mongoRoutes);

// Unknown routes -> JSON 404
app.use((req, res) => {
    res.status(404).json({ error: 'Route not found' });
});

module.exports = app;