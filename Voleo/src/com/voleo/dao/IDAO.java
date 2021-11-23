package com.voleo.dao;

import java.util.Collection;

public interface IDAO<T> {
	public void add(T t);
	public void remove(T t);
	public void removeById(Long id);
	public void update(T t);
	public Collection<T> getAll();
	public T getById(Long id);
}
