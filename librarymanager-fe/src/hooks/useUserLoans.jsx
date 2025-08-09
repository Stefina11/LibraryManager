import { useState, useEffect } from 'react';
import api from '../api/api.js';

function useUserLoans({ userId, token }) {
  const [userLoans, setUserLoans] = useState([]);
  const [loadingLoans, setLoadingLoans] = useState(false);
  const [errorLoans, setErrorLoans] = useState(null);

  const fetchLoans = async () => {
    setLoadingLoans(true);
    setErrorLoans(null);

    try {
      if (userId || token) {
        const res = await api.get('/loans/user');
        setUserLoans(Array.isArray(res.data) ? res.data : []);
      } else {
        setUserLoans([]);
      }
    } catch (err) {
      setErrorLoans(err.message || 'Errore caricamento prestiti');
      setUserLoans([]);
    } finally {
      setLoadingLoans(false);
    }
  };

  useEffect(() => {
    if (userId || token) {
      fetchLoans();
    }
  }, [userId, token]);

  const returnLoan = async (loanId) => {
    const url = `/loans/return/${loanId}`;
    await api.post(url);
    await fetchLoans();
  };

  return { userLoans, loadingLoans, errorLoans, fetchLoans, returnLoan };
}

export default useUserLoans;
