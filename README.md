# Advanced-Programming-2-Android

Welcome to ChitChat are Last assignment chat website/app.

## Table of Contents

- [How to Run](#how-to-run)
- [Login Screen](#login-screen)
- [Registration Screen](#registration-screen)
- [Chat Screen](#chat-screen)
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



### Setting
Here you can change the settings of the app which are the change the server address and or the theme, to see the changes in action you need to press "Apply".
Note that if you change the server address you will be kickout to the main screen, and logged out.



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




