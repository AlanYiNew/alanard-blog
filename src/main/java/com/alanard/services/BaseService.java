package com.alanard.services;
import org.springframework.stereotype.Service;

import com.alanard.pojo.Post;


@Service
public interface BaseService<T> {
	
	public int add(T obj);			
	public T update(T obj);	
	public int delete(T obj);			
	public T get(T obj);
		
}
