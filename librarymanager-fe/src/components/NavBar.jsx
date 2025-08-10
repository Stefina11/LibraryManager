import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import '../css/NavBar.css';
import { FaSignOutAlt } from 'react-icons/fa'; 

function NavBar() {
  const { isAuthenticated, userRole, logout } = useAuth();

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <div className="navbar-logo">
          <img className="navbar-image" src="/images/book.png" alt="Logo" />
          <Link to="/">LibraryManager</Link>
        </div>
        <div className="navbar-links">
          <Link to="/">Home</Link>
          {!isAuthenticated && (
            <>
              <Link to="/auth/login">Login</Link>
              <Link to="/auth/register">Registrati</Link>
            </>
          )}

          {isAuthenticated && (
            <>
              <Link to="/profile">Il mio profilo</Link>
              {userRole === 'ADMIN' && (
                <Link to="/admin/add-book" className="admin-btn">
                  Aggiungi Libro
                </Link>
              )}
            </>
          )}
        </div>
      </div>

      {isAuthenticated && (
        <button onClick={logout} className="navbar-logout" title="Logout">
          <FaSignOutAlt size={20} />
        </button>
      )}
    </nav>
  );
}

export default NavBar;
