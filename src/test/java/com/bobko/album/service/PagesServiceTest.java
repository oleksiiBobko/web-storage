package com.bobko.album.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bobko.album.dao.interfaces.IPagesHolderDao;

public class PagesServiceTest {

    @Mock
    private IPagesHolderDao pagesDao;
    
    @InjectMocks
    private PagesService pagesService;
    
    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void test() {
        when(pagesDao.getShift()).thenReturn(4);
        assertEquals(pagesService.getShift(), 4);
    }

}
