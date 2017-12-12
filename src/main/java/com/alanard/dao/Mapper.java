package com.alanard.dao;

public interface Mapper<T> {

	public int add(T obj);
		
	public boolean update(T obj);
		
	public int delete(T obj);

	public T get(T obj);
}
