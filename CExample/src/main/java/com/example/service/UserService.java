package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import com.example.model.User2;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Cacheable(value = "userCache", key = "#p0", unless = "#result==null")
	public User2 findOne(int id) {
     System.out.println("cache");
		return userRepository.findOne(id);
	}
	
	@Cacheable(value="userCache", key = "#p0")
	public void save(User2 user) {

		userRepository.save(user);

	}
	
	@Cacheable(value="userCache")
	public List<User2> findAll() {
		ArrayList<User2> arrayList = new ArrayList<>();
		userRepository.findAll().forEach(arrayList::add);
		return arrayList;
	}
	
	@CacheEvict(value="userCache", key = "#p0")
	public void delectUser(int id) {
		userRepository.delete(id);
		
	}
	
	@CachePut(value="userCache", key = "#p0")
	public void updateUser(User2 user,String id) {
	
		userRepository.save(user);
		
	}
	
	
	
	


}
