import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import api from '../api/api.js';
import useUserLoans from '../hooks/useUserLoans';
import SearchBar from './SearchBar';
import BookCard from './BookCard';
import '../css/Home.css';
import '../App.css';

const Home = () => {
  const { userId, token } = useAuth();
  const [query, setQuery] = useState('');
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [hasSearched, setHasSearched] = useState(false);

  const {
    userLoans,
    loadingLoans,
    errorLoans,
    fetchLoans,
    returnLoan,
  } = useUserLoans({ userId, token });

const handleSearch = async (q) => {
  setHasSearched(true);
  setLoading(true);
  setError(null);
  
  try {
    let response;
    if (!q.trim()) {
      response = await api.get('/books');
    } else {
      response = await api.get('/books/search', { params: { query: q } });
    }
    setBooks(response.data);
  } catch (err) {
    console.error(err);
    setError('Errore durante la ricerca dei libri');
    setBooks([]);
  } finally {
    setLoading(false);
  }
};

  const onBorrowSuccess = (bookId) => {
    setBooks(prevBooks =>
      prevBooks.map(book =>
        book.id === bookId ? { ...book, available: false } : book
      )
    );
    fetchLoans();
  };

  const onReturnSuccess = async (loanId, bookId) => {
    try {
      await returnLoan(loanId);
      setBooks(prevBooks =>
        prevBooks.map(book =>
          book.id === bookId ? { ...book, available: true } : book
        )
      );
      fetchLoans();
    } catch (err) {
      console.error('Errore durante la restituzione:', err);
    }
  };

  return (
    <div className="home-container">
      <h1>Benvenuto in LibraryManager</h1>
      <img className="home-image" src="/images/bookImg.jpg" alt="Libro" />
      <h2>Consulta la nostra biblioteca, e trova il tuo libro</h2>

      <SearchBar query={query} setQuery={setQuery} onSearch={handleSearch} />

      {error && <p className="error-message">{error}</p>}
      {loading && <p className="loading-message">Caricamento in corso...</p>}

      {errorLoans && <p className="error-message">Errore caricamento prestiti: {errorLoans}</p>}
      {loadingLoans && <p className="loading-message">Caricamento prestiti in corso...</p>}

      {books.length > 0 && !loading && (
        <div className="grid-books">
          {books.map(book => {
            const loan = Array.isArray(userLoans) ? userLoans.find(l => l.bookId === book.id) : null;

            return (
              <BookCard
                key={book.id}
                book={book}
                alreadyLoaned={Boolean(loan)}
                loanId={loan?.loanId}
                onBorrowSuccess={onBorrowSuccess}
                onReturnSuccess={() => {
                  if (loan?.loanId) {
                    onReturnSuccess(loan.loanId, book.id);
                  }
                }}
              />
            );
          })}
        </div>
      )}

      {hasSearched && !loading && books.length === 0 && (
        <p className="no-result-message">Nessun libro trovato.</p>
      )}
    </div>
  );
};

export default Home;
