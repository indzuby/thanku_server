var maria = {
    // host:'192.168.99.100',
    host:'localhost',
    user:'thanks',
    password:'Eodtm0192!!',
    db:'thanks'
};

var redis = {
    // host : '192.168.99.100',
    host:'localhost',
    port : 6379,
    retryStrategy : function(time) {

    }
};

module.exports = {
    maria:maria,
    redis:redis,
    gcmKey:'AIzaSyC11NoX9MteRlPfhbx31rMChjARn7_enZo'
};
