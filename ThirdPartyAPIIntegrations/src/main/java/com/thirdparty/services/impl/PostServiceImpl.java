package com.thirdparty.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.thirdparty.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private RestTemplate restTemplate;

//	Third party API
	String baseUrl = "https://jsonplaceholder.typicode.com/";

	String POST = "posts";
	@Override
	public List<Map<String, Object>> getPosts() {
		HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
		String url = baseUrl + POST;
		ResponseEntity<List> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return response.getBody();
	}

	String POSTBYID = "posts/";
	@Override
	public Map<String, Object> getPostById(int id) {
		HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
		String url = baseUrl + POSTBYID + id;
		System.out.println(url);
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new RestClientException("Post is not found");
		}
		return response.getBody();
	}

	@Override
	public Map<String, Object> insertPosts(Map<String, Object> payload) {
		HttpEntity<Map> httpEntity = new HttpEntity<>(payload, getHttpHeaders());
		String url = baseUrl + POST;
		System.out.println(url);
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return response.getBody();
	}

	@Override
	public Map<String, Object> updatePostById(Map<String, Object> payload, int id) {
		HttpEntity<Map> httpEntity = new HttpEntity<>(payload, getHttpHeaders());
		String url = baseUrl + POSTBYID + id;
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Map.class);
		} catch (RestClientException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return response.getBody();
	}

	@Override
	public Map<String, Object> deleteById(int id) {
		HttpEntity<Map> httpEntity = new HttpEntity<>(getHttpHeaders());
		String url = baseUrl + POSTBYID + id;
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Map.class);
		} catch (RestClientException e) {
			throw new RuntimeException(e);
		}
		return response.getBody();
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

}
