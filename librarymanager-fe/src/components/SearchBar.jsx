import '../css/SearchBar.css';

const SearchBar = ({ onSearch, query, setQuery }) => {
  
  const handleSubmit = (e) => {
    e.preventDefault();
    onSearch(query);
  };

  return (
    <form onSubmit={handleSubmit} className="flex gap-2 w-full max-w-md">
      <input
        type="text"
        placeholder="Cerca per titolo, autore o categoria..."
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        className="flex-1 border p-2 rounded"
      />
      <button type="submit" >
        Cerca
      </button>
    </form>
  );
};

export default SearchBar;
