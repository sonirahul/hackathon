package com.donateknowledge.dao;

import java.util.List;

import org.bson.Document;

public interface IBlogPostDAO {

	void likePost(String permalink, int ordinal);

	Document findByPermalink(String permalink);

	List<Document> findByDateDescending(int limit);

	List<Document> findByTagDateDescending(String tag);

	String addPost(String title, String body, List tags, String username);

	void addPostComment(String name, String email, String body, String permalink);

}
