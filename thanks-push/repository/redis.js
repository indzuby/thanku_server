var Redis = require('ioredis');
var dbInfo = require('../conf');
var push = require('../service/push');
var subRedis = new Redis(dbInfo.redis);
var redis = new Redis(dbInfo.redis);

redis.connect(function() {
    console.log('connect redis!');
});

subRedis.connect(() => {
    console.log('connect redis on subscribe!');
    initRedisSubscribe();
});

function saveRiderLocation(lat, lon, token) {
    redis.geoadd(['rider', lat, lon, token], function (data, data2) {
        console.log('add lat : %lf lon : %lf token:%s %s', lat, lon, token, data2);
    });
}


// 레디스에 설정해야할 것을 한다
function initRedisSubscribe() {
    subRedis.subscribe(['select', 'order'], function(err, count) {
        console.log('no of subscribe channel %s', count);
        console.log('error -> %s', err);
    });


    // 위에서 수신한(subscribe) 한 메시지를 핸들링한다.
    subRedis.on('message', function(channel, message) {
        console.log("receive message from channel %s", channel);
        var parsed = JSON.parse(message);
        if(channel == 'order') {
            // lat lon rad unit notification_data message_data
            redis.georadius('rider', parsed.lat, parsed.lon, parsed.distance, parsed.unit, function(data, data2, data3) {
                console.log('err %s users %s', data, data2);
                push.sendOrderMessage(data2, parsed.notification, parsed.data);
            });
        } else if(channel=='select') {
            push.sendSelectMessage(parsed.tokens, parsed.notification, parsed.data);
        }
    });

    // 바로 위와 같은 역할 하지만 듣기로는 에러가 발생했을때 여기만 반응한다고 한다.
    subRedis.on('messageBuffer', function (channel, message) {});
}

function sendMessage(message) {
    var param = JSON.parse(message);
    // todo find rider and send message

    //keys, message, data
    push.sendMessage();
}


function printCommand() {
    console.log(subRedis.getBuiltinCommands());
}



module.exports = {
    saveRiderLocation:saveRiderLocation
};
