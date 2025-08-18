import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import './index.css'
import './css/AddBook.css';
import './css/BookCard.css';
import './css/BookDetail.css';
import './css/Footer.css';
import './css/Home.css';
import './css/Login.css';
import './css/NavBar.css';
import './css/ProfilePage.css';
import './css/Register.css';
import './css/SearchBar.css';

import './App.css'

import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
