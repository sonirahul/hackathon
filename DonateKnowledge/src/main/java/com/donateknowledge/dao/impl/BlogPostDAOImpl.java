/*
 * Copyright 2013-2015 MongoDB Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.donateknowledge.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_POSTS;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.configurator.ApplicationConfigurator;
import com.donateknowledge.dao.IBlogPostDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

@Repository
public class BlogPostDAOImpl implements IBlogPostDAO {
	private final MongoCollection<Document> collection;

	@Autowired(required = true)
	public BlogPostDAOImpl(ApplicationConfigurator appConfig) {
		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_POSTS));
	}

	@Override
	public Document findByPermalink(String permalink) {
		Document post = collection.find(eq("permalink", permalink)).first();

		// fix up if a post has no likes
		if (post != null) {
			List<Document> comments = (List<Document>) post.get("comments");
			for (Document comment : comments) {
				if (!comment.containsKey("num_likes")) {
					comment.put("num_likes", 0);
				}
			}
		}
		return post;
	}

	@Override
	public List<Document> findByDateDescending(int limit) {
		return collection.find().sort(descending("date"))
				.limit(limit)
				.into(new ArrayList<Document>());
	}

	@Override
	public List<Document> findByTagDateDescending(final String tag) {
		return collection.find(eq("tags", tag))
				.sort(descending("date"))
				.limit(10)
				.into(new ArrayList<Document>());
	}

	@Override
	public String addPost(String title, String body, List tags, String username) {
		String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
		permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
		permalink = permalink.toLowerCase();

		Document post = new Document("title", title)
				.append("author", username)
				.append("body", body)
				.append("permalink", permalink)
				.append("tags", tags)
				.append("comments", new ArrayList())
				.append("date", new Date());

		collection.insertOne(post);

		return permalink;
	}

	@Override
	public void addPostComment(final String name, final String email, final String body, final String permalink) {
		Document comment = new Document("author", name)
				.append("body", body);

		if (email != null && !email.isEmpty()) {
			comment.append("email", email);
		}

		collection.updateOne(eq("permalink", permalink),
				new Document("$push", new Document("comments", comment)));
	}

	@Override
	public void likePost(final String permalink, final int ordinal) {

		collection.updateOne(new BasicDBObject("permalink", permalink), new BasicDBObject("$inc", new BasicDBObject("comments." + ordinal + ".num_likes", 1)));

	}
}
