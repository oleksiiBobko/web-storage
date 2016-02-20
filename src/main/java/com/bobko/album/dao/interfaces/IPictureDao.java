package com.bobko.album.dao.interfaces;

import java.util.List;

public interface IPictureDao<E, K> {

    public void add(E entity);

    public void update(E entity);

    public void remove(E entity);

    public E find(K key);

    public List<E> list();

    public List<E> rankList(int shift, int count);

}
