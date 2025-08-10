import React, { useState } from 'react';
import api from '../api/api'; 
import { useNavigate } from 'react-router-dom';
import '../css/Register.css';


function Register() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); 
            if (!firstName.trim() || !lastName.trim() || !password.trim() || !email.trim()) {
        setError('Tutti i campi sono obbligatori.');
        return;
    }

        try {
            const response = await api.post('/auth/signup', {  
                firstName: firstName.trim(),
                lastName: lastName.trim(),
                password: password.trim(),
                email: email.trim(),
            });

            if (response.status === 200) {
                console.log('Registrazione avvenuta con successo:', response.data);
                navigate('/auth/login');
              
            } else {
                setError('Errore durante la registrazione: ' + response.statusText);
            }
        } catch (err) {
            setError('Errore durante la registrazione: ' + err.message);
            console.error('Errore durante la registrazione:', err);
        }
    };

    return (
        <div className="register-container">
            <h2>Registrazione</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSubmit} className="register-form">
                <div>
                    <label>Nome:</label>
                    <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
                </div>
                <div>
                    <label>Cognome:</label>
                    <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} required />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                <div>
                    <label>Email:</label>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                </div>
                <button type="submit">Registrati</button>
            </form>
        </div>
    );
}

export default Register;