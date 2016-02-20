package com.bobko.album.dao.base;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, PK extends Serializable> {

    public void add(T entity);

    public void update(T entity);

    public void remove(T entity);

    public T find(PK key);

    public List<T> list();

    public List<T> rankList(int shift, int count);
    
    public List<T> getByField(String field, String value);

}
