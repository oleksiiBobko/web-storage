package com.bobko.album.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureDAO
 */

import java.util.List;

import com.bobko.album.domain.Picture;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDAOImpl implements PictureDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addPicture(Picture pic) {
        sessionFactory.getCurrentSession().save(pic);
    }
    
    @SuppressWarnings("unchecked")
    public List<Picture> list(int shift, int count) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Picture.class);
        criteria.setFirstResult(shift * count);
        criteria.setMaxResults(count);
        criteria.addOrder(Order.desc("created"));
        return criteria.list();
    }

    public Picture getPicture(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (Picture)session.get(Picture.class, id);
    }    
    
    public void removePicture(Integer id) {
        Picture pic = (Picture) sessionFactory.getCurrentSession().load(
                Picture.class, id);
        if (null != pic) {
            sessionFactory.getCurrentSession().delete(pic);
        }

    }

}
