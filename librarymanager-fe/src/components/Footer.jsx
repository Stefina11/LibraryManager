import React from 'react';
import '../css/Footer.css';

function Footer() {
  return (
    <footer className="footer">
      <p>© {new Date().getFullYear()} LibraryManager. Tutti i diritti riservati.</p>
    </footer>
  );
}

export default Footer;
