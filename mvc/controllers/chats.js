const chatsService = require('../services/chats.js');
const tokensController = require('./tokens.js');
const User = require('../services/users.js');
const firebase = require('../models/firebase.js');
const usersController = require('../controllers/users.js');

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
        let otherUser = await User.getUserByUsername(req.body.username);
        if (!otherUser) {
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
    const user = await usersController.getUserByUsername(username);
    const chat = await chatsService.addNewMessageByChatId(username, req.params.id, req.body.msg);
    if (!chat) {
        return res.status(404).json({ error: 'chat not found' });
    }

    const admin = require('firebase-admin');

    // Initialize the Firebase Admin SDK with your JSON key file
    admin.initializeApp({
        // put the private key hear
        credential: admin.credential.cert('../config/fir-example-d231a-firebase-adminsdk-516di-944bfc49a8.json'),
    });
    // Define the notification payload
    const tokensFirebase = firebase.tokens;
    const tokenFirebase = tokensFirebase.find(token => token.username === chat.users[1].username);


    const message = {
        notification: {
            title: `${user.displayName} sent you a message`,
            body: `${req.body.msg}`,
        },
        // Specify the target device token(s) or topic
        token: `${tokenFirebase}`,
    };
    // Send the notification
    admin.messaging().send(message);

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
