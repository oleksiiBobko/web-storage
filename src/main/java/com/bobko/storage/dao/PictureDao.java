package com.bobko.storage.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureDAO
 */

import org.springframework.stereotype.Repository;

import com.bobko.storage.dao.base.HibernateDao;
import com.bobko.storage.dao.interfaces.IPictureDao;
import com.bobko.storage.domain.Document;

@Repository
public class PictureDao extends HibernateDao<Document, Integer> implements
        IPictureDao<Document, Integer> {
}
