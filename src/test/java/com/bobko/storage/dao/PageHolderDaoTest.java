package com.bobko.storage.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.bobko.storage.dao.PageHolderDao;

public class PageHolderDaoTest {

    @InjectMocks
    private PageHolderDao holder;
    
    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shiftShoulBeZerotest() {
        assertEquals(0, holder.getShift());
    }

}
