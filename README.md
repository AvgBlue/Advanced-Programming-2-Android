# Advanced-Programming-2-Android

Welcome to ChitChat are Last assignment chat website/app.

## Table of Contents

- [How to Run](#how-to-run)
- [App](#app)
> - [Main Screen](main-screen)
> - [Setting](setting)
> - [Login Screen](login-screen)
> - [Login Screen](login-screen)
- [Website](#website)
> - [Login Screen](#login-screen)
> - [Registration Screen](#registration-screen)
> - [Chat Screen](#chat-screen)
- [Credits](#credits)

## How to Run
You will need to npm,node.js,React and React Router.

Clone the repository:
```
gh repo clone nello0b/Advanced-Programming-2-Webserver
```

you can run the server with the comand line: 

```
cd Advanced-Programming-2-Android
cd mvc
npm install
npm start
```

To run the app you will need to open this project with Android Studio, and build it there (we trust your intelligence and experience in the matter).

Now you can enter [http://localhost:5000/](http://localhost:5000/) to browse our chat site.

## App

### Main Screen
In the Main Screen you can choose if you want to login or to register, also you can press the ⚙️ to get into the [Chat Screen](#Setting). to change the theme or the server address.

<img width="185" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/9bcd1379-ed84-44e8-9c7a-9f569500572a">
<img width="186" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/e41646f7-ab53-4f98-bdfa-b2662a2e6b50">

### Setting
Here you can change the settings of the app which are the change the server address and or the theme, to see the changes in action you need to press "Apply".
Corrently we have 2 themes: light and dark. Note that if you change the server address you will be kickout to the main screen, and logged out.
To get out of the Settings screen press the back button.

<img width="186" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/8e4c58f7-93db-471b-8d59-6b3b2206101f">
<img width="185" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/cee2285f-c65f-44d3-b0c1-0914f20a71c9">

### Login Screen
In the Login Screen you will be able to log into your user with your password, if the login is secsessful you will enter the up, if there is an error with your username or password, or the server is not available, a toast message will be printed with the appropriate information.

<img width="183" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/c2f4768a-b025-47a8-94df-63f8f2d8eeee">
<img width="186" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/2461bd5c-aa7a-4033-abbf-d597ea4a7df9">

### Registration Screen
In the Registration Screen you will be able to open an account, note that you need to provide a username, a display name, a password 8 characters long with at least one letter and one number, and an very cool image. After that press register. If everything went ok, you now will be able to navigate back to the [Login Screen](login-screen) and get into the app with your new user.

<img width="183" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/70410cd6-c852-4dc3-b054-317379ad8a8f">
<img width="184" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/7180e85a-313d-4fa2-bcb8-ff514b9c2eee">

### Chats Screen
In the Chats Screen you will be able to choose a 1 of your active conversations, and start talking with 1 of your friends. Alternatively you can press the ➕ button at the bottom right and add a new user to your conversations at the [Add Chat Screen](add-chat-screen). Also now you can see our logout button that will log you out of our app, so you can enter with a new user.

<img width="182" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/3079f3d6-0851-491d-90dc-73cfd735c52c">
<img width="185" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/7f3292ec-c11e-4037-a16c-824def66bb1f">

### Add Chat Screen

In the Add Chat Screen you will be able enter the infromations of a a user you want to start a conversations with, and if that user exists and is not in a conversation with you, a new conversation will be added to your [Chats Screen](chats-screen).

<img width="183" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/e752ee36-f2ab-4dff-afc1-511ac2f0d36f">
<img width="184" alt="image" src="https://github.com/AvgBlue/Advanced-Programming-2-Android/assets/72969087/586d5f57-31d9-4c58-a864-35bab51de923">

### Add Chat Screen

## Website

### Login Screen
In the Login Screen you will be able to login to ChitChat be entering your username and password and pressing to the Login button, and then the user will continue to the [Chat Screen](#chat-screen).

if you don't have a user, you will be needing to create a user be pressing to the "Click here" link continue to the [Registration Screen](#registration-screen)

you won't be able to login with a worng username or password and you will get this response: "username or password is incorrect"

Here is a preview of what the login screen looks like:

<img width="562" alt="image" src="https://github.com/nello0b/Advanced-Programming-2-Webserver/assets/116730693/7d3277f5-6d97-45ce-a3f9-4158e17d02cb">

### Registration Screen

In the registration screen you able to select your username, password, Display name and picture.
after filling your details your feel free to press register button and you will go back to the [Login Screen](#login-screen)

- username needs to be at most 20 characters
- Password needs to be at least 8 charcters and at most 20 characters and be made by numbers and letters
- Display name needs to be at least 2 charcters and at most 20 characters and by made by only letters
- you must upload a picture.

every division from from this formal will display an appropriate error message.


Here is a preview of what the registration screen looks like:

<img width="562" alt="image" src="https://github.com/nello0b/Advanced-Programming-2-Webserver/assets/116730693/3faddc95-1c59-4bf5-b691-0a8f7ae7236c">


### Chat Screen
In the chat screen the you will be able to send and receive messages, add new Contacts, search existing contacts and logout from the app.

After selecting a contact you want to chat will you will be able to send them a message be inputing the message in the message input in the bottom part of the chatscreen, and afther clicking the send button.

To add new contacts you will be needing to press 👤➕ Icons, you will able to only add existing users as contracts try to add contacts you already added or non-existing users, you will get an appropriate alert.

you will be able to search between you contcats with the search bar be inputing your desire contact and pressing on the search button.

Here is a preview of what the chat screen looks like:

<img width="559" alt="image" src="https://github.com/nello0b/Advanced-Programming-2-Webserver/assets/116730693/584da3fe-940e-40fd-a69a-74b88f199d42">


## Credits

this assignment was made be:

- Netanel Berkovits
- David Berkovits
- Bar Aharon




