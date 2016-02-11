package com.bobko.album.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bobko.album.common.AlbumConst;
import com.bobko.album.domain.AlbumPage;
import com.bobko.album.domain.Picture;
import com.bobko.album.service.PictureService;
import com.bobko.album.service.interfaces.IPagesService;
import com.bobko.album.service.interfaces.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-context.xml", "classpath:application-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class UploadControllerTest {
   
    private Picture picture;
    private List<Picture> pictures;
    private List<AlbumPage> pages;
    
    @Autowired
    private WebApplicationContext wac;
   
    private MockMvc mockMvc;

    @InjectMocks
    private UploadController uploadController;
   
    @Mock
    private PictureService pictureService;
    
    @Mock
    private IPagesService pagesService;

    @Mock
    private IUserService userService;
   
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        pictures = new ArrayList<Picture>();
        picture = new Picture();
        
        picture.setDescription("on vocation");
        picture.setFilename("1.jpg");
        picture.setId(3);
        picture.setOwner("alex");
        picture.setPath("/1/2");
       
        Picture picture2 = new Picture();
       
        picture2.setDescription("description2");
        picture2.setFilename("2.jpg");
        picture2.setId(4);
        picture2.setOwner("alex1");
        picture2.setPath("/2/3");        
        
        pictures.add(picture);
        pictures.add(picture2);
       
        Mockito.when(pictureService.list(0, AlbumConst.PICTURE_COUNT)).thenReturn(pictures);
        
        pages = new ArrayList<AlbumPage>();
        pages.add(new AlbumPage(0, true));
        pages.add(new AlbumPage(1, false));
        Mockito.when(pagesService.list()).thenReturn(pages);
        
        this.mockMvc = MockMvcBuilders.standaloneSetup(uploadController).build();

    }

    @Test
    public void testPictures() throws Exception {        
        
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/content");
//       
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        
//        mockMvc.perform(requestBuilder).
//                andExpect(status().isOk()).
//                andExpect(model().attribute("pictures", pictures)).
//                andExpect(model().attribute("pages", pages)).
//                andExpect(model().size(4)).
//                andExpect(view().name("pictures-list"));
    }
    
//    @Test
    public void testHome() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        mockMvc.perform(requestBuilder).andExpect(status().isFound()).andExpect(view().name("redirect:/content"));
    }
    
    @Test
    public void testAddNew() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/add");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(view().name("upload"));
    }
    
    @Test
    public void testSave() throws Exception {
        
        MockMultipartHttpServletRequest multipartRequest = new MockMultipartHttpServletRequest();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "orig", null, "bar".getBytes());
        multipartRequest.addFile(mockMultipartFile);
        MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload("/save").file(mockMultipartFile);

        requestBuilder.
        param("owner", "alex").
        param("description", "on vocation").
        param("filename", "1.jpg");
        
        mockMvc.perform(requestBuilder).andExpect(status().isFound()).andExpect(view().name("redirect:/content"));
    }
    
    @Test
    public void testGetPicture() throws Exception {
        
    }    
    
    @Test
    public void testDeletePicture() throws Exception {
        
    }
    
    @Test
    public void testLogin() throws Exception {
        
    }
    
    @Test
    public void testGrabb() throws Exception {
        
    }
    
    @Test
    public void testNextPage() throws Exception {
        
    }
    
    @Test
    public void testPrevPage() throws Exception {
        
    }
    
    @Test
    public void testGoToPage() throws Exception {
        
    }
    
}