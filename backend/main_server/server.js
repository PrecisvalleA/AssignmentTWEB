const app = require('./app');
const http = require('http');
const { Server } = require('socket.io');

const PORT = process.env.PORT || 3000;

const server = http.createServer(app);

// Inizializza Socket.IO
const io = new Server(server, {
    cors: {
        origin: "*", // oppure specifica il tuo frontend (es. "http://localhost:63342")
        methods: ["GET", "POST"]
    }
});

// Socket.IO: gestione connessioni
io.on('connection', (socket) => {
    console.log('🔌 New client connected');

    socket.on('joinRoom', (movieId) => {
        socket.join(movieId);
        console.log(`👤 Client joined movie room: ${movieId}`);
    });

    socket.on('chatMessage', ({ movieId, username, message }) => {
        const payload = { username, message, timestamp: new Date() };
        // Invia a tutti nella stanza tranne chi ha inviato
        socket.to(movieId).emit('chatMessage', payload);
        // (Opzionale: salva su DB o log)
        console.log(`💬 [${movieId}] ${username}: ${message}`);
    });

    socket.on('disconnect', () => {
        console.log('❌ Client disconnected');
    });
});

server.listen(PORT, () => {
    console.log(`🚀 Main server listening on port ${PORT}`);
});
