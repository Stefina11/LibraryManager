import React, { useEffect, useState } from "react";
import jwtDecode from "jwt-decode";
import useUserLoans from "../hooks/useUserLoans";
import '../css/ProfilePage.css';


function ProfilePage({ token }) {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);

  const email = getEmailFromToken(token);

  // hook
  const {
    userLoans: loans,
    loadingLoans: loading,
    errorLoans,
    returnLoan,
  } = useUserLoans({ token });

  // Recupera info utente (solo una volta se c’è l’email)
  useEffect(() => {
    if (!email) {
      setError("Token non valido o email non trovata");
      return;
    }

    async function fetchUserData() {
      try {
        const res = await fetch(`http://localhost:8081/users/by-email?email=${email}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        if (!res.ok) throw new Error("Errore nel recupero dati utente");
        const data = await res.json();
        setUser(data);
      } catch (e) {
        setError(e.message);
      }
    }

    fetchUserData();
  }, [email, token]);

  // Gestione click su "Restituisci"
  async function handleReturn(loanId) {
    try {
      await returnLoan(loanId);
    } catch (err) {
      alert(err.message);
    }
  }

  // Estrae l’email dal token
  function getEmailFromToken(token) {
    if (!token) return null;
    try {
      const decoded = jwtDecode(token);
      return decoded.sub || decoded.email || decoded.username;
    } catch (e) {
      console.error("Token non valido", e);
      return null;
    }
  }

  if (loading) return <div>Caricamento...</div>;
  if (error || errorLoans) return <div>Errore: {error || errorLoans}</div>;

  return (
    <div className="profile-container">
      <h2>Ti diamo il benvenuto, {user?.firstName}!</h2>

      <h3>I tuoi prestiti attivi:</h3>
      {loans.length === 0 ? (
        <p>Nessun prestito attivo</p>
      ) : (
        <ul>
          {loans.map((loan) => (
            <li key={loan.loanId}>
                <img
    src={
      loan.bookCoverImage ||
      (loan.bookIsbn
        ? `https://covers.openlibrary.org/b/isbn/${loan.bookIsbn}-M.jpg`
        : 'https://via.placeholder.com/100x150.png?text=No+Cover')
    }
    alt={`Copertina di ${loan.bookTitle}`}
    className="loan-cover"
  />
  <div className="loan-info">
              <strong>{loan.bookTitle}</strong> 
              <br />
               {loan.bookAuthor} 
               <br />
              Scadenza: <span className="due-date">{loan.dueDate}</span>
              <br />
              <button onClick={() => handleReturn(loan.loanId)}>Restituisci</button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default ProfilePage;
