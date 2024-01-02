package com.thirdparty.services;

import java.util.List;
import java.util.Map;

public interface PostService {
	List<Map<String, Object>> getPosts();

	Map<String, Object> getPostById(int id);

	Map<String, Object> insertPosts(Map<String, Object> payload);

	Map<String, Object> updatePostById(Map<String, Object> payload, int id);

	Map<String, Object> deleteById(int id);
}
