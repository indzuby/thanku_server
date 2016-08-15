var express = require('express');
var router = express.Router();
var redis = require('../repository/redis');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.send('test!');
});

router.get('/location', function(req, res, next) {
    var lat = req.query.lat;
    var lon = req.query.lon;
    var userToken = req.query.token;
    console.log(lat + ' ' + lon);
    if(!(lat && lon)) {
        sendError(400, 'Not location found', next);
    } else {
        redis.saveRiderLocation(lat, lon, userToken);
        res.sendStatus(204);
    }
});

router.post('/push', function(req, res, next) {

    console.log(req.body);
    console.log(req.body.lat);
    console.log(req.body.lon);
    console.log(req.body.message);

    res.sendStatus(200);
});


module.exports = router;
