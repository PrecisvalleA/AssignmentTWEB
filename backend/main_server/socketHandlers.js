const app = require('./app');
const http = require('http');
const { Server } = require('socket.io');



// Socket.io initialization

function initSocket(io) {
    console.log('[socket] chat namespace ready');
    const chatNsp = io.of('/chat');

    chatNsp.on('connection', (socket) => {
        console.log('client', socket.id, 'connected to /chat');

        socket.on('joinRoom', (movieId) => {
            socket.join(movieId);
            console.log(`${socket.id} joined movie room: ${movieId}`);
        });

        socket.on('chatMessage', ({ movieId, username = 'User', message }) => {
            chatNsp.to(movieId).emit('chatMessage', {
                username,
                message,
                timestamp: Date.now()
            });
        });
    });
}

module.exports = {initSocket};


