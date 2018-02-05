const express = require('express');
const path = require('path');
const morgan = require('morgan');

const app = express();

app.use(morgan('short'));

app.use(express.static(__dirname + '/dist'));

app.get('*', function(req, res) {
  res.sendFile(path.join(__dirname + '/dist/index.html'));
});

app.listen(process.env.PORT || 4200);
