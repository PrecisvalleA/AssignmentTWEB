const express = require('express');
const cors = require('cors');
const springRoutes = require('./routes/springRoutes');
const mongoRoutes = require('./routes/mongoRoutes');

const app = express();
const PORT = 5000; // porta del main server

app.use(cors());
app.use(express.json());

app.use('/spring', springRoutes); // richieste dirette al server Spring Boot
app.use('/mongo', mongoRoutes);   // richieste dirette al server MongoDB Express

app.listen(PORT, () => {
  console.log(`✅ Main server in ascolto su http://localhost:${PORT}`);
});
