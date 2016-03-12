package com.donateknowledge.dto.product.book;

import java.util.Date;

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
    private String secretCode;
    private boolean available = true;
    private boolean tempBlocked=false;
    private Date tempBlockedOn;
    private String tempBlockedBy;
    
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
	
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public boolean isTempBlocked() {
		return tempBlocked;
	}
	public void setTempBlocked(boolean tempBlocked) {
		this.tempBlocked = tempBlocked;
	}
	public Date getTempBlockedOn() {
		return tempBlockedOn;
	}
	public void setTempBlockedOn(Date tempBlockedOn) {
		this.tempBlockedOn = tempBlockedOn;
	}
	public String getTempBlockedBy() {
		return tempBlockedBy;
	}
	public void setTempBlockedBy(String tempBlockedBy) {
		this.tempBlockedBy = tempBlockedBy;
	}
	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", authorName=" + authorName + ", publisherName=" + publisherName
				+ ", isbn=" + isbn + ", edition=" + edition + ", year=" + year + ", genre=" + genre + ", secretCode="
				+ secretCode + ", available=" + available + ", tempBlocked=" + tempBlocked + ", tempBlockedOn="
				+ tempBlockedOn + ", tempBlockedBy=" + tempBlockedBy + ", toString()=" + super.toString() + "]";
	}
}
