
	
	package com.dtos;

import jakarta.validation.constraints.NotBlank;

public class BookDto {

	    @NotBlank(message = "Il titolo è obbligatorio")
	    private String title;
	    @NotBlank(message = "L'autore è obbligatorio")
	    private String author;
	    @NotBlank(message = "La categoria è obbligatoria")
	    private String category;
	    private boolean available;    
	    private String isbn;

	    // Constructor to add books:
	    public BookDto(String title, String author, String category) {
	        this.title = title;
	        this.author = author;
	        this.category = category;
	    }
	    
//	    completed constructors 
	    public BookDto(String title, String author, String category, boolean available, String isbn) {
	        this.title = title;
	        this.author = author;
	        this.category = category;
	        this.available = available;
	        this.isbn = isbn;
	    }
	    
	    public BookDto() {}
	    
	    
	    
	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getAuthor() {
	        return author;
	    }

	    public void setAuthor(String author) {
	        this.author = author;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public void setCategory(String category) {
	        this.category = category;
	    }
	    
	    public boolean isAvailable() {
	        return available;
	    }

	    public void setAvailable(boolean available) {
	        this.available = available;
	    }
	    
	    public String getIsbn() {
	        return isbn;
	    }

	    public void setIsbn(String isbn) {
	        this.isbn = isbn;
	    }
	}



