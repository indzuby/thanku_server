var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var routes = require('./routes/index');
var redis = require('./repository/redis');

var app = express();
app.set('env', 'development');
// uncomment after placing your favicon in /public
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(function(req, res, next) {
    var agent = req.get('User-Agent');
    console.log(agent);

    // if('thanks-server' != agent) {
    //
    //     sendError(406, 'Not acceptable', next);
    //     return ;
    // }
    var contentType = req.get('Content-Type');
    if('application/json' != contentType) {
        sendError(400, 'Wrong data type', next);
    }

    next();
});

app.use('/', routes);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    console.error(err);
    res.send(err);
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.send(err.message);
});


function sendError(errorCode, message, next) {
    var err = new Error(message);
    err.status=errorCode;
    next(err);
}

module.exports = app;
