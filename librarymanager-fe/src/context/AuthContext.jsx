import React, { createContext, useState, useEffect, useContext } from 'react';
import jwt_decode from 'jwt-decode';
import api from '../api/api';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [userId, setUserId] = useState(null);
  const [userEmail, setUserEmail] = useState(null);
  const [userRole, setUserRole] = useState(null);
  const [token, setToken] = useState(localStorage.getItem('jwtToken'));

  // LOGIN
  const login = (newToken) => {
    localStorage.setItem('jwtToken', newToken);
    setToken(newToken); 
  };

  // LOGOUT
  const logout = () => {
    localStorage.removeItem('jwtToken');
    setToken(null);
    setIsAuthenticated(false);
    setUserId(null);
    setUserEmail(null);
    setUserRole(null);
  };


  useEffect(() => {
    if (!token) {
      setIsAuthenticated(false);
      setUserId(null);
      setUserEmail(null);
      setUserRole(null);
      return;
    }

    let decoded;
    try {
      decoded = jwt_decode(token);
      console.log('🔐 Decoded token:', decoded);
    } catch (e) {
      console.error('Errore decoding JWT:', e);
      localStorage.removeItem('jwtToken');
      setToken(null);
      return;
    }

    const email = decoded.sub;
    const roles = decoded.roles || [];

 
console.log('🟢 Sto per chiamare users/by-email con token:', token);
 api.get('/users/by-email', {
    params: { email },               
    headers: { Authorization: `Bearer ${token}` }
  })

.then((res) => {
  console.log('Risposta utente:', res.data);
  setUserId(res.data.id);
  setUserEmail(email);
  setUserRole(roles.includes('ROLE_ADMIN') ? 'ADMIN' : 'USER');
  setIsAuthenticated(true);
})
  .catch((err) => {
    console.error('⚠️ Errore nel recupero dell’ID utente:, axio ha lanciato errore', err);
  });

  }, [token]);

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        userId,
        userEmail,
        userRole,
        login,
        logout,
        token,  
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
 export const useAuth = () => useContext(AuthContext);