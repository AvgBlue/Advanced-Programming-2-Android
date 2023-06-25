var tokens = [];


const addNewToken = ((username, token) => {
    const tokenExists = tokens.some((item) => item.token === token);
  
    if (!tokenExists) {
        tokens.push({
            username: username,
            token: token
        });
    }
});

const getTokens = (() => {
    return tokens;
});

module.exports = { addNewToken, getTokens }