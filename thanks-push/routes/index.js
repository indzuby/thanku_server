var express = require('express');
var router = express.Router();
var redis = require('../repository/redis');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.send('test!');
});

router.post('/location', function(req, res, next) {
    var lat = req.body.lat;
    var lon = req.body.lon;
    var userToken = req.body.token;
    console.log(lat + ' ' + lon);
    if(!(lat && lon)) {
        sendError(400, 'Not location found', next);
        return ;
    }
    if(!userToken || userToken.trim().length <= 0) {
        sendError(400, 'Not token found', next);
        return ;
    }

    redis.saveRiderLocation(lat, lon, userToken);
    res.sendStatus(204);
});

router.post('/push', function(req, res, next) {

    console.log(req.body);
    console.log(req.body.lat);
    console.log(req.body.lon);
    console.log(req.body.message);

    res.sendStatus(200);
});


module.exports = router;
