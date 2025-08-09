import React, { useState } from 'react';
import api from '../api/api';
import { useNavigate, Link } from 'react-router-dom';
import '../css/Login.css';
import { useAuth } from '../context/AuthContext';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();
    const [showPassword, setShowPassword] = useState(false);
    const { login } = useAuth();   


    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        setError('');

        try {
            const response = await api.post('/auth/login', {
                email: email.trim(),
                password: password
            });

            if (response.data.token) {
                localStorage.setItem('jwtToken', response.data.token);
                login(response.data.token); 
                console.log('Token salvato:', response.data.token);
                navigate('/'); // return home
            }

        } catch (err) {
            const errorMessage = err.response?.data?.message ||
                               'Credenziali non valide o errore del server';
            setError(errorMessage);
            console.error('Errore login:', err.response?.data || err.message);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="login-container">
            <h2>Accesso all'Account</h2>

            {error && <div className="error-message">{error}</div>}

            <form onSubmit={handleSubmit} className="login-form">
                <div className="form-group">
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        autoComplete="username"
                        placeholder="esempio@email.com"
                    />
                </div>

                <div className="form-group">
                    <label>Password:</label>
                    <div className="password-container">
                        <input
                            type={showPassword ? "text" : "password"}
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            autoComplete="current-password"
                            minLength={8}
                        />
                        <button
                            type="button"
                            onClick={() => setShowPassword(!showPassword)}
                            className="toggle-password-btn"
                        >
                            {showPassword ? "🙈" : "👁️"}
                        </button>
                    </div>
                </div>

                <button
                    type="submit"
                    disabled={isLoading}
                    className="submit-btn"
                >
                    {isLoading ? 'Accesso in corso...' : 'Accedi'}
                </button>
            </form>

            <div className="additional-links">
                <Link to="/auth/register">Crea un nuovo account</Link>
                <Link to="/password-reset">Recupera password</Link>
            </div>
        </div>
    );
}

export default Login;
