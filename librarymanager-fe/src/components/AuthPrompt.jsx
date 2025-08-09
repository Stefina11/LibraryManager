import { Link } from "react-router-dom";
import { useAuth } from '../context/AuthContext';

const AuthPrompt = () => {
  const { isAuthenticated } = useAuth();  

  if (isAuthenticated) return null;  
  

  return (
    <div className="text-center">
      <p className="mb-2">Per prendere in prestito o restituire libri, accedi o registrati:</p>
      <div className="flex gap-4 justify-center">
        <Link to="/auth/login" className="text-blue-600 hover:underline">Login</Link>
        <Link to="/auth/register" className="text-blue-600 hover:underline">Registrati</Link>
      </div>
    </div>
  );
};

export default AuthPrompt;
