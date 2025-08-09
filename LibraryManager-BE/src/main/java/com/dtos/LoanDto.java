package com.dtos;

import java.time.LocalDate;

public class LoanDto {
	private Integer loanId;
    private Integer bookId;
    private Integer userId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;
    
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private String bookCoverImage; 

    
    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBookIsbn() {
        return bookIsbn;
    }
    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }
    public String getBookCoverImage() {
        return bookCoverImage;
    }
    public void setBookCoverImage(String bookCoverImage) {
        this.bookCoverImage = bookCoverImage;
    }
}