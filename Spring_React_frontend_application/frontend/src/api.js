// src/api.js

import axios from 'axios';

const API = axios.create({
    baseURL: 'http://localhost:8080/api/auth',
});

export default API;
