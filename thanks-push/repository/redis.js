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
    if(!token || token.trim().length <= 0) {
        return ;
    }
    redis.geoadd(['rider', lon, lat, token], function (data) {
        console.log('add lat : %s lon : %s token:%s save count : %s', lat, lon, token, data);
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
        console.log("receive message from channel %s", channel, message);
        var parsed = JSON.parse(message);
        if(channel == 'order') {
            orderPush(parsed);
        } else if(channel=='select') {
            push.sendSelectMessage(parsed.target, parsed.notification, parsed.data);
        }
    });

    // 바로 위와 같은 역할 하지만 듣기로는 에러가 발생했을때 여기만 반응한다고 한다.
    subRedis.on('messageBuffer', function (channel, message) {});
}

function orderPush(parsed) {
    var tokenSet = new Set();
    for (var index in parsed.target) {
        // lat lon rad unit notification_data message_data
        console.log('lat : %s lon : %s distance : %s', parsed.target[index].lat, parsed.target[index].lon, parsed.distance);
        redis.georadius('rider', parsed.target[index].lon, parsed.target[index].lat, parsed.distance, parsed.unit, function(data, tokens) {
            console.log('err %s users %s, %s', data, tokens);
            tokens = tokens.toString().split(',');

            for(var index in tokens) {
                tokenSet.add(tokens[index]);
            }
            
            var target = [];
            tokenSet.forEach(function(tokenKey, tokenValue){target.push(tokenValue);});
            console.log(tokenSet, target);
            push.sendOrderMessage(target, parsed.notification, parsed.data);
        });
    }

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
