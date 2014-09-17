package com.bobko.album.dao.factory;

import com.bobko.album.dao.interfaces.IPictureDao;
import com.bobko.album.dao.interfaces.IUserDao;

public interface IDaoFactoryMySql {

	public IPictureDao getPictureDao();
	
	public IUserDao getUserDao();
	
	
}
