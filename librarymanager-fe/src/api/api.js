// 
import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8081',

    headers: {
        'Content-Type': 'application/json'
    }
});



api.interceptors.request.use(config => {
    const token = localStorage.getItem('jwtToken');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});


// Interceptor per gestire errori 401 (solo per le richieste protette)
api.interceptors.response.use(
    response => response,
    error => {
        if (error.response?.status === 401) {
            localStorage.removeItem('jwtToken');
            window.location.href = '/login'; // Redirect alla pagina di login
        }
        return Promise.reject(error);
    }
);

export default api;
