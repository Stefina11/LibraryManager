# 📚 LibraryManager

**LibraryManager** è una piattaforma intuitiva per la gestione dei prestiti librari.  
Permette agli utenti di cercare, prendere in prestito e restituire libri, mentre gli amministratori possono arricchire l'archivio aggiungendo nuovi titoli.

## ⚙️ Requisiti

- **Java 17** o superiore
- **Maven**
- **MySQL**
- **Node.js** e **npm**

- ## 📖 Funzionalità

- **Ricerca pubblica**: qualunque utente può consultare il catalogo tramite:
  - Titolo
  - Categoria
  - Autore
- **Card dei libri**: ogni libro è mostrato in una card contenente:
  - Copertina
  - Titolo
  - Autore
  - Categoria
  - Codice ISBN
- **Prestito e restituzione**:
  - Utenti registrati e loggati possono prendere in prestito e restituire libri.
  - Visualizzazione della scadenza del prestito nella pagina personale.
  - Stato del libro:
    - Pulsante **"Prendi in prestito"** quando disponibile.
    - Indicazione **"Non disponibile"** se già in prestito.
    - Pulsante **"Restituisci"** se il libro è stato preso in prestito dall’utente loggato.
- **Pagina di dettaglio**:
  - Mostra ulteriori informazioni e la descrizione del libro (quando disponibile tramite OpenLibrary).
- **Gestione Admin**:
  - Gli amministratori possono aggiungere nuovi libri inserendo il codice ISBN, che permette di recuperare automaticamente copertina e descrizione.

---

##  Istruzioni di avvio

### 1 Avvio del Backend (`LibraryManager-BE`)

1. Installare **Java 17**, **Maven** e **MySQL**.
2. Creare un database MySQL, `library_manager` (nome specificato in `application.properties`).
3. Configurare utente e password MySQL in: src/main/resources/application.properties
4. Effettuare l'import di `LibraryManager-BE` sull'IDE utilizzato (es. Eclipse o IntelliJ)


### 2 Avvio del Frontend (`LibraryManager-fe`)
1. Installare Node.js e npm.

Da terminale:
  ```bash
  cd LibraryManager-FE
  npm install
  npm run dev
```
2. Effettuare l'import di `LibraryManager-fe` sull'IDE utilizzato (es. Visual Studio Code)

##  Istruzioni creazione utenza admin
L'utente di default viene creato con utenza ROLE_USER. Per creare un'utenza admin in grado di aggiungere libri all'archivio l'installatore esclusivamente deve creare l'utenza tramite modifica del
metodo "signup" nel file AthenticationService.java:
<img width="373" height="121" alt="image" src="https://github.com/user-attachments/assets/cfd6c929-2196-43ec-93b0-954f6715667d" />

.setRole(Role.ROLE_USER) deve essere MOMENTANEAMENTE sostituito con .setRole(Role.ROLE_ADMIN)


