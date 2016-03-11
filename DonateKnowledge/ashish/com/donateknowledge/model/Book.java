package com.donateknowledge.model;

import java.sql.Blob;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="BOOKS")
public class Book implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="BOOKNO")
    private Integer bookNo;
    @Column(name="BOOKNAME")
    @NotEmpty(message="Book name is mandatory")
    private String bookName;
    @Column(name="AUTHOR1NO")
    @NotNull(message="Author name is mandatory")
    private Integer author1No;
    @Column(name="AUTHOR2NO")
    private Integer author2No;
    @Column(name="AUTHOR3NO")
    private Integer author3No;
    @Column(name="AUTHOR4NO")
    private Integer author4No;
    @Column(name="PUBLISHERNO")
    @NotNull(message="Publisher name is mandatory")
    private Integer publisherNo;
    @Column(name="CATEGORYNO")
    @NotNull(message="Category is mandatory")
    private Integer categoryNo;
    @Column(name="COVERPAGE")
    @Lob
    private Blob coverPage;
    @Column(name="ISBN")
    @NotEmpty(message="ISBN is mandatory")
    private String isbn;
    @Column(name="EDITION")
    @NotEmpty(message="Edition is mandatory")
    private String edition;
    @Column(name="YEAR")
    @NotNull(message="Year is mandatory")
    private Integer year;
    @Column(name="COST")
    @NotNull(message="Cost is mandatory")
    private Integer cost;
    @Column(name="SYNOPSIS")
    @NotEmpty(message="Synopsis is mandatory")
    private String synopsis;
    @Column(name="ABOUTAUTHORS")
    @NotEmpty(message="About Authors is mandatory")
    private String aboutAuthors;
    @Column(name="TOPICSCOVERED")
    private String topicsCovered;
    @Column(name="CONTENTSCDROM")
    private String contentsCDROM;
    @Column(name="TOC")
    @Lob
    private Blob toc;
    @Column(name="SAMPLECHAPTER")
    @Lob
    private Blob sampleChapter;
    @Column(name="HITS")
    private Integer hits;

    public String getAboutAuthors() {
        return aboutAuthors;
    }
    public void setAboutAuthors(String aboutAuthors) {
        this.aboutAuthors = aboutAuthors;
    }

    public Integer getAuthor1No() {
        return author1No;
    }
    public void setAuthor1No(Integer author1No) {
        this.author1No = author1No;
    }

    public Integer getAuthor2No() {
        return author2No;
    }
    public void setAuthor2No(Integer author2No) {
        this.author2No = author2No;
    }

    public Integer getAuthor3No() {
        return author3No;
    }
    public void setAuthor3No(Integer author3No) {
        this.author3No = author3No;
    }

    public Integer getAuthor4No() {
        return author4No;
    }
    public void setAuthor4No(Integer author4No) {
        this.author4No = author4No;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookNo() {
        return bookNo;
    }
    public void setBookNo(Integer bookNo) {
        this.bookNo = bookNo;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }
    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getContentsCDROM() {
        return contentsCDROM;
    }
    public void setContentsCDROM(String contentsCDROM) {
        this.contentsCDROM = contentsCDROM;
    }

    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Blob getCoverPage() {
        return coverPage;
    }
    public void setCoverPage(Blob coverPage) {
        this.coverPage = coverPage;
    }

    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getHits() {
        return hits;
    }
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublisherNo() {
        return publisherNo;
    }
    public void setPublisherNo(Integer publisherNo) {
        this.publisherNo = publisherNo;
    }

    public Blob getSampleChapter() {
        return sampleChapter;
    }
    public void setSampleChapter(Blob sampleChapter) {
        this.sampleChapter = sampleChapter;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Blob getToc() {
        return toc;
    }
    public void setToc(Blob toc) {
        this.toc = toc;
    }

    public String getTopicsCovered() {
        return topicsCovered;
    }
    public void setTopicsCovered(String topicsCovered) {
        this.topicsCovered = topicsCovered;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
}
