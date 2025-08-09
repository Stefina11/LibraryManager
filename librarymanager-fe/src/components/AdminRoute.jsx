

import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const AdminRoute = ({ children }) => {
    const { isAuthenticated, userRole } = useAuth();
    if (!isAuthenticated || userRole !== 'ADMIN') {
        return <Navigate to="/accesso-negato" replace />;
      }
      return children;
    };

export default AdminRoute;
