import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import api from '../api/api';
import '../css/BookCard.css';
import { toast } from 'react-toastify';


const BookCard = ({ book, alreadyLoaned, loanId, onBorrowSuccess, onReturnSuccess }) => {
  const { isAuthenticated, userId } = useAuth();
  const isAvailable = book.available;

  const [loading, setLoading] = useState(false);

  const handleBorrow = async () => {
    setLoading(true);
    try {
      const response = await api.post('/loans/add', {
        bookId: book.id,
        userId: userId,
      });
      toast.success(response.data);
      onBorrowSuccess(book.id);
    } catch (error) {
      console.error(error);
      toast.error('Errore nel prendere in prestito il libro.');
    } finally {
      setLoading(false);
    }
  };

  const handleReturn = async () => {
    if (!loanId) {
      toast.error('Errore: loanId mancante.');
      return;
    }
    setLoading(true);
    try {
      const response = await api.post(`/loans/return/${loanId}`);
      toast.success(response.data);
      onReturnSuccess(loanId, book.id);
    } catch (error) {
      console.error(error);
      toast.error('Errore nella restituzione del libro.');
    } finally {
      setLoading(false);
    }
  };

  const coverSrc =
    book.coverImage ||
    (book.isbn
      ? `https://covers.openlibrary.org/b/isbn/${book.isbn}-M.jpg`
      : 'https://via.placeholder.com/150x220.png?text=No+Cover');

  return (
    <div className="book-card-container">
      <div className="book-card">
        <img
          src={coverSrc}
          alt={`${book.title} di ${book.author || 'Autore sconosciuto'}`}
        />
      </div>

      <div className="book-card-content">
        <div className="book-info">
          <h2>{book.title}</h2>
          <p><strong>Autore:</strong> {book.author || 'Non disponibile'}</p>
          <p><strong>Categoria:</strong> {book.category || 'Non disponibile'}</p>
          <p><strong>ISBN:</strong> {book.isbn || 'Non disponibile'}</p>
          <p>
            {isAvailable ? (
              <span >Disponibile</span>
            ) : (
              <span >Non disponibile</span>
            )}
          </p>
        </div>

        <div>
          {isAuthenticated && isAvailable && (
            <button
              onClick={handleBorrow}
              className="btn btn-primary"
              disabled={loading}
            >
              {loading ? 'Attendi...' : 'Prendi in prestito'}
            </button>
          )}
          {isAuthenticated && alreadyLoaned && (
            <button
              onClick={handleReturn}
              className="btn btn-secondary"
              disabled={loading}
            >
              {loading ? 'Attendi...' : 'Restituisci'}
            </button>
          )}
          <div className="details-button-container">
            <Link to={`/books/${book.id}`} className="details-link">
              Dettagli
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BookCard;
