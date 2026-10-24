/**
 * Socket.IO chat.
 *
 * One namespace (/chat) and one room per movie (the room name is the movie id),
 * so every movie details page has its own isolated chat.
 */
const MAX_MESSAGE_LENGTH = 500;
const MAX_USERNAME_LENGTH = 30;

/** Normalizes a movie id received from a client; returns null if it is not a numeric id. */
function toRoom(movieId) {
    const room = String(movieId ?? '').trim();
    return /^\d+$/.test(room) ? room : null;
}

/**
 * Registers the chat handlers.
 * @param {import('socket.io').Server} io the Socket.IO server
 */
function initSocket(io) {
    const chatNsp = io.of('/chat');
    console.log('[socket] chat namespace ready');

    chatNsp.on('connection', (socket) => {
        let currentRoom = null;

        socket.on('joinRoom', (movieId) => {
            const room = toRoom(movieId);
            if (!room) return;
            if (currentRoom) socket.leave(currentRoom);
            socket.join(room);
            currentRoom = room;
        });

        socket.on('chatMessage', (payload) => {
            const { movieId, username, message } = payload || {};
            const room = toRoom(movieId);

            // only clients that joined the room can write in it
            if (!room || !socket.rooms.has(room)) return;

            const text = String(message ?? '').trim().slice(0, MAX_MESSAGE_LENGTH);
            if (!text) return;
            const user = String(username ?? '').trim().slice(0, MAX_USERNAME_LENGTH) || 'Guest';

            chatNsp.to(room).emit('chatMessage', {
                username: user,
                message: text,
                timestamp: Date.now()
            });
        });
    });
}

module.exports = { initSocket };