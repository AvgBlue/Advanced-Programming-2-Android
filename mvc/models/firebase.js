var tokens = [];

var addNewToken = function addNewToken(username, token) {
    tokens.push({
        username: username,
        token: token
    });
    }
    
module.exports = { addNewToken, tokens }