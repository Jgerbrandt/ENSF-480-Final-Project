import axios from 'axios';
import React from 'react';

const USERS_REST_API_URL = 'http://localhost:8080/api/users';

class UserService{

    getUsers(){
        return (axios.get(USERS_REST_API_URL));
    }



    async postLogin(email, password){
        let newUser = {name: ' ', address: ' ', email: email, password: password, creditCardNum: ' '}
        await axios.post(USERS_REST_API_URL, newUser);
    }

    async post(newUser){
        await axios.post(USERS_REST_API_URL, newUser);
    }

}

export default new UserService();