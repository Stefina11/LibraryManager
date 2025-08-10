import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import '../css/BookDetail.css';

const BookDetail = () => {
  const { id } = useParams();
  const [book, setBook] = useState(null);
  const [description, setDescription] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchBookAndDescription = async () => {
      try {
        const bookRes = await fetch(`http://localhost:8081/books/${id}`);
        if (!bookRes.ok) {
          throw new Error('Libro non trovato nel database');
        }
        const bookData = await bookRes.json();
        setBook(bookData);

  
        if (bookData.isbn) {
          const openLibRes = await fetch(`https://openlibrary.org/isbn/${bookData.isbn}.json`);
          if (!openLibRes.ok) {
            throw new Error('Descrizione non trovata su OpenLibrary');
          }
          const openLibData = await openLibRes.json();

          if (openLibData.description) {
            if (typeof openLibData.description === 'string') {
              setDescription(openLibData.description);
            } else if (openLibData.description.value) {
              setDescription(openLibData.description.value);
            }
          } else {
            setDescription('Nessuna descrizione disponibile.');
          }
        } else {
          setDescription('ISBN mancante per questo libro');
        }
      } catch (err) {
        console.error(err);
        setError(err.message);
      }
    };

    fetchBookAndDescription();
  }, [id]);

  if (error) {
    return <div className="book-detail-error">{error}</div>;
  }

  if (!book) {
    return <div className="book-detail-loading">Caricamento...</div>;
  }

  const coverSrc =
    book.coverImage || 
    (book.isbn
      ? `https://covers.openlibrary.org/b/isbn/${book.isbn}-L.jpg`
      : 'https://via.placeholder.com/200x300.png?text=No+Cover');

  return (
    <div className="book-detail-container">
      <h1>{book.title}</h1>

      {/* Qui metti la copertina */}
      <div className="book-detail-cover">
        <img
          src={coverSrc}
          alt={`Copertina di ${book.title}`}

        />
      </div>

      <p className="book-detail-meta"><strong>Autore:</strong> {book.author}</p>
      <p className="book-detail-meta"><strong>Categoria:</strong> {book.category}</p>
      <p className="book-detail-meta"><strong>ISBN:</strong> {book.isbn || 'Non disponibile'}</p>

      <h2 >Descrizione</h2>
      <p>{description}</p>
    </div>
  );
};

export default BookDetail;
