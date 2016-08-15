var dbParam = require('../conf');
var MariaClient = require('mariasql');

var client = new MariaClient(dbParam);


// token을 가지는 사용자를 찾는다 없으면 maria에서 데이터를 가져온다
function findUser(token, success, error) {
    var param = {id:token};
    client.query('SELECT * FROM oauth_access_token WHERE authentication_id=:id',
        param, function (err, rows) {
            if(err) {
                if(error) {
                    error(err);
                } else {
                    throw err;
                }
            } else {
                success(rows);
            }
        });
}

module.exports= {
    findUser:findUser
};
