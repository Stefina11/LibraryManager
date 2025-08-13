import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login.jsx';
import Register from './components/Register.jsx';
import AddBook from './components/AddBook.jsx';
import AdminRoute from './components/AdminRoute.jsx';
import AccessDenied from './components/AccessDenied.jsx';
import ProfilePage from './components/ProfilePage';
import Home from './components/Home.jsx';
import NavBar from './components/NavBar.jsx';
import Footer from './components/Footer.jsx';
import './App.css';
import BookDetail from './components/BookDetail';
import { AuthProvider , useAuth} from './context/AuthContext'; 

import { ToastContainer, Slide } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './css/Toast.css';

function ProfileWrapper() {
  const { token, logout } = useAuth();

  if (!token) {
    // Se non c'è token, reindirizza alla pagina di login
    return <Navigate to="/auth/login" />;
  }

  return <ProfilePage token={token} logout={logout} />;
}

function App() {
    return (
        <AuthProvider>
        <Router>
            <NavBar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/auth/login" element={<Login />} />
                <Route path="/auth/register" element={<Register />} />
                <Route path="/profile" element={<ProfileWrapper />} />
                <Route path="/books/:id" element={<BookDetail />} />
                <Route path="/admin/add-book" element={
                    <AdminRoute> 
                        <AddBook /> 
                    </AdminRoute>
                } />
                <Route path="/accesso-negato" element={<AccessDenied />} />
            </Routes>
            <Footer /> 
            <ToastContainer position="top-center" 
                            autoClose={4000} 
                            hideProgressBar={true} 
                            closeOnClick pauseOnHover   
                            draggable
                            theme="colored"
                            transition={Slide} />
                            
        </Router>
        
        </AuthProvider>
    );
}

export default App;
