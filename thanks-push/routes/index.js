var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.send('test!');
});

router.get('/location', function(req, res, next) {
    var lat = req.query.lat;
    var lon = req.query.lon;
    var userToken = '';
    console.log(lat + ' ' + lon);
    if(!(lat && lon)) {
        sendError(400, 'Not location found', next);
    } else {
        res.sendStatus(204);
    }
});

router.post('/push', function(req, res, next) {
    var agent = req.get('User-Agent');
    console.log(agent);
    if('thanks-server' != agent) {
        sendError(406, 'Not acceptable', next);
        return ;
    }
    var contentType = req.get('Content-Type');
    if('application/json' != contentType) {
        sendError(400, 'Wrong data type', next);
    }

    console.log(req.body);
    console.log(req.body.lat);
    console.log(req.body.lon);
    console.log(req.body.message);

    res.sendStatus(200);
});

function sendError(errorCode, message, next) {
    var err = new Error(message);
    err.status=errorCode;
    next(err);
}

module.exports = router;
