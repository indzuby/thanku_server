var gcm = require('node-gcm');
var conf = require('../conf');
var sender = new gcm.Sender(conf.gcmKey);

function sendMessage(keys, message, data) {
    var gcmMessage = new gcm.Message();
    // gcmMessage.setNotification('title', 'Thanks');
    // gcmMessage.setNotification('body', message);
    gcmMessage.addNotification(message);
    gcmMessage.addData(data);

    sender.send(gcmMessage, {registrationTokens:keys}, 10, function(err, response) {
        if(err) console.error(err);
        else console.log(response);
    });
}

module.exports = {sendMessage : sendMessage};
