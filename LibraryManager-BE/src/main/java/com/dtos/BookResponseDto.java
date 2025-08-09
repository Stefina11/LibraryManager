package com.dtos;

public class BookResponseDto {

	private Integer id;
    private String title;
    private String author;
    private String category;
    private boolean available;
    private String isbn;

    public BookResponseDto(Integer id, String title, String author, String category,boolean available, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
        this.isbn = isbn;
    }
	
    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public String getIsbn() {return isbn;}
}
