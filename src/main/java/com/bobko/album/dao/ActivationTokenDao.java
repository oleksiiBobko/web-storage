package com.bobko.album.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDAO
 */

import org.springframework.stereotype.Repository;

import com.bobko.album.dao.base.HibernateDao;
import com.bobko.album.dao.interfaces.IActivationTokenDao;
import com.bobko.album.domain.ActivationToken;

@Repository
public class ActivationTokenDao extends HibernateDao<ActivationToken, String>
        implements IActivationTokenDao {}
