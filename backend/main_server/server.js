/**
 * Main Express API Gateway Server
 *
 * This server acts as a central API gateway, forwarding requests
 * to both the Spring Boot server and the MongoDB Reviews server.
 */

const express = require('express');
const cors = require('cors');
const springRoutes = require('./routes/springRoutes');
const mongoRoutes = require('./routes/mongoRoutes');

const app = express();

// Enable Cross-Origin Resource Sharing for frontend access
app.use(cors());
// Parse incoming JSON requests
app.use(express.json());

// Sub-routes
app.use('/spring', springRoutes);
app.use('/mongo', mongoRoutes);

// Start server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Main server listening on port ${PORT}`);
});