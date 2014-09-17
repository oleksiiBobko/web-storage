package com.bobko.album.dao.base;

import java.util.List;

public interface GenericDao<E, K> {

    public void add(E entity);


    public void update(E entity);

    public void remove(E entity);
    
    public E find(K key);

    public List<E> list();
	
}
