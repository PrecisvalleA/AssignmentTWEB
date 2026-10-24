/**
 * Main Express application for the Reviews Microservice.
 *
 * Responsible for:
 * - Connecting to MongoDB
 * - Routing API requests
 * - Handling errors
 */
var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const swaggerUi = require('swagger-ui-express');
const swaggerJsdoc = require('swagger-jsdoc');

var app = express();

const mongoose = require('mongoose');

// MongoDB Connection Setup
mongoose.connect(process.env.MONGO_URL || 'mongodb://localhost:27017/reviews')
    .then(() => console.log('Connected to MongoDB'))
    .catch(err => console.error('MongoDB connection error:', err));
// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

// Middleware setup
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// Swagger setup
const options = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Movie Reviews API',
      version: '1.0.0',
      description: 'API for managing movie reviews',
    },
  },
  apis: ['./routes/*.js'],
};

const specs = swaggerJsdoc(options);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(specs));

// Import and mount reviews router
const reviewsRouter = require('./routes/reviews');
app.use('/reviews', reviewsRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// Global error handler: this is a JSON API, so errors are returned as JSON (not HTML pages)
app.use(function(err, req, res, next) {
  res.status(err.status || 500).json({ message: err.message });
});


module.exports = app;
