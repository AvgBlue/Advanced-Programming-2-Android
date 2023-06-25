const chatsService = require('../services/chats.js');
const tokensController = require('./tokens.js');
const firebase = require('../services/firebase.js');
const User = require('../services/users.js');

const admin = require('firebase-admin');
const serviceAccount = require('../config/advanced-programming-2-android-firebase-adminsdk-u82e9-e8d7f4a76c.json');

// Initialize the Firebase Admin SDK with the JSON key object
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

const getAllChats = async (req, res) => {
    const username = await tokensController.getUsernameFromToken(req, res);
    const chats = await chatsService.getAllChats(username);
    if (!chats) {
        return res.status(404).json({ error: 'chats not found' });
    }
    res.status(200).json(chats); 
}

const addNewChat = async (req, res) => {    
    const username = await tokensController.getUsernameFromToken(req, res);
    const chat = await chatsService.addNewChat(username, req.body.username);
    if (!chat) {
        let otherUser =  await User.getUserByUsername(req.body.username);
        if(!otherUser){
            return res.status(400).json({ error: 'the inputted user doesnt exists' });
        }
        return res.status(404).json({ error: 'chat not found' });
    }
    res.status(200).json(chat); 
}

const getFullChatById = async (req, res) => {
    const username = await tokensController.getUsernameFromToken(req, res);    
    const chat = await chatsService.getFullChatById(username, req.params.id);
    if (!chat) {
        return res.status(404).json({ error: 'chat not found' });
    }
    res.status(200).json(chat); 
}

const deleteFullChatById = async (req, res) => {
    const chat = await chatsService.deleteFullChatById(req.params.id);
    if (!chat) {
        return res.status(404).json({ error: 'chat not found' });
    }
    res.status(200).json(chat); 
}

const addNewMessageByChatId = async (req, res) => {
    const username = await tokensController.getUsernameFromToken(req, res);  
    const user = await User.getUserByUsername(username);
    const chat = await chatsService.addNewMessageByChatId(username, req.params.id, req.body.msg);
    if (!chat) {
        return res.status(404).json({ error: 'chat not found' });
    }
    // Define the notification payload
    const tokensFirebase = firebase.getTokens();
    const tokenFirebase = tokensFirebase.find(token => token.username === chat[0].users[1].username).token;
   
    const message = {
        notification: {
            title: user.displayName + " sent you a message",
            body: req.body.msg,
        },
        // Specify the target device token(s) or topic
        token: tokenFirebase,
    };
    // Send the notification
    admin.messaging().send(message).
        then((response) => {
            console.log('notification sent succesfully:', response);
        })
        .catch((err) => {
            console.error('error sending notification:', err);
        });
   
    res.status(200).json(chat);
    
}

const getAllMessagesById = async (req, res) => {
    const username = await tokensController.getUsernameFromToken(req, res);
    const chat = await chatsService.getAllMessagesById(username, req.params.id);
    if (!chat) {
        return res.status(404).json({ error: 'chat not found' });
    }
    res.status(200).json(chat); 
}

module.exports = { getAllChats, addNewChat, getFullChatById, deleteFullChatById, addNewMessageByChatId, getAllMessagesById }
