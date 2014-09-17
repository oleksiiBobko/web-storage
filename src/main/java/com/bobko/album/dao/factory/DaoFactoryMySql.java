package com.bobko.album.dao.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bobko.album.dao.interfaces.IPictureDao;
import com.bobko.album.dao.interfaces.IUserDao;


@Component
public class DaoFactoryMySql implements IDaoFactoryMySql {

	@Autowired
	private IPictureDao pictureDao;
	
	@Autowired
	private IUserDao userDao;	
	
	@Override
	public IPictureDao getPictureDao() {
		return pictureDao;
	}
	
	@Override
	public IUserDao getUserDao() {
		return userDao;
	}
	
}
