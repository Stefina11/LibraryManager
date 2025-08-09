import React, { useState} from 'react';
import api from '../api/api';
import { useAuth } from '../context/AuthContext';
import '../css/AddBook.css';

function AddBook() {
    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [category, setCategory] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const { userRole } = useAuth();
    const [isbn, setIsbn] = useState('');
  
    

    if (userRole !== 'ADMIN') {
        return (
            <div>
                <h2>Accesso Negato</h2>
                <p>Non sei autorizzato ad accedere a questa pagina.</p>
            </div>
        );
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage('');
        setError('');

         if (!title.trim() || !author.trim() || !category.trim() || !isbn.trim()) {
        setError("Tutti i campi sono obbligatori.");
        return;
    }

        try {
            const bookDto = {
                title: title.trim(),
                author: author.trim(),
                category: category.trim(),
                isbn: isbn.trim(),
            };

            const response = await api.post('/books/add', bookDto);
            setMessage(response.data);
            setTitle('');
            setAuthor('');
            setCategory('');
            setIsbn('');;
        } catch (err) {
            setError(err.response?.data || 'Errore durante l\'aggiunta del libro.');
            console.error('Errore durante l\'aggiunta del libro:', err);
        }
    };

    return (
        <div className='add-book-container'> 
            <h2>Aggiungi un Nuovo Libro</h2>
            {message && <p style={{ color: 'green' }}>{message}</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Titolo:</label>
                    <input
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Autore:</label>
                    <input
                        type="text"
                        value={author}
                        onChange={(e) => setAuthor(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Categoria:</label>
                    <input
                        type="text"
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                        required
                    />
                </div>

                <div>
                        <label>ISBN:</label>
                         <input
                          type="text"
                          value={isbn}
                          onChange={(e) => setIsbn(e.target.value)}
                          required
                     />
                </div>

                <button type="submit">Aggiungi</button>
            </form>
        </div>
    );
}

export default AddBook;