package com.bobko.storage.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDAO
 */

import org.springframework.stereotype.Repository;

import com.bobko.storage.dao.base.HibernateDao;
import com.bobko.storage.dao.interfaces.IActivationTokenDao;
import com.bobko.storage.domain.ActivationToken;

@Repository
public class ActivationTokenDao extends HibernateDao<ActivationToken, String>
        implements IActivationTokenDao {}
