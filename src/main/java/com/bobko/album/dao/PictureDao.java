package com.bobko.album.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureDAO
 */

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bobko.album.dao.base.HibernateDao;
import com.bobko.album.dao.interfaces.IPictureDao;
import com.bobko.album.domain.Pictures;

@Component
@Repository
public class PictureDao extends HibernateDao<Pictures, Integer> implements
        IPictureDao<Pictures, Integer> {
}
