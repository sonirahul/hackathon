package com.donateknowledge.dto.product.book;

import com.donateknowledge.dto.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book extends Product {

    private String bookTitle;
    private String authorName;
    private String publisherName;
    private String isbn;
    private String edition;
    private Integer year;
    private String genre;
    private String secertCode;
    
    public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
		super.setProductId(isbn);
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getSecertCode() {
		return secertCode;
	}
	public void setSecertCode(String secertCode) {
		this.secertCode = secertCode;
	}
	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", authorName=" + authorName + ", publisherName=" + publisherName
				+ ", isbn=" + isbn + ", edition=" + edition + ", year=" + year + ", toString()="
				+ super.toString() + "]";
	}
}
