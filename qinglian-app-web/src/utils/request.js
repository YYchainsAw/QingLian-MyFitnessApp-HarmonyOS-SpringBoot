import axios from 'axios'

const request = axios.create({
    baseURL: '/api', // This uses the proxy defined in vite.config.js
    timeout: 10000
})

// Request interceptor
request.interceptors.request.use(
    config => {
        // You can add token here if needed
        // const token = localStorage.getItem('token');
        // if (token) {
        //     config.headers['Authorization'] = `Bearer ${token}`;
        // }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
)

// Response interceptor
request.interceptors.response.use(
    response => {
        return response.data;
    },
    error => {
        console.error('API Error:', error)
        return Promise.reject(error);
    }
)

export default request;

