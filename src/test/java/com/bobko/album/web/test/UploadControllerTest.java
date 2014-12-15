package com.bobko.album.web.test;

import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;










import com.bobko.album.domain.*;
import com.bobko.album.web.*;
import com.bobko.album.service.*;
import com.bobko.album.service.interfaces.IPagesService;
import com.bobko.album.service.interfaces.IUserService;

import org.mockito.*; 
import org.junit.*;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:META-INF/test-context.xml", "classpath:META-INF/application-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class UploadControllerTest {
   
    private Pictures picture;
    private List<Pictures> pictures;
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

        pictures = new ArrayList<Pictures>();
        picture = new Pictures();
        
        picture.setDescription("on vocation");
        picture.setFilename("1.jpg");
        picture.setId(3);
        picture.setOwner("alex");
        picture.setPath("/1/2");
       
        Pictures picture2 = new Pictures();
       
        picture2.setDescription("description2");
        picture2.setFilename("2.jpg");
        picture2.setId(4);
        picture2.setOwner("alex1");
        picture2.setPath("/2/3");        
        
        pictures.add(picture);
        pictures.add(picture2);
       
        Mockito.when(pictureService.list(0, IPagesService.PICTURE_COUNT)).thenReturn(pictures);
        
        pages = new ArrayList<AlbumPage>();
        pages.add(new AlbumPage(0, true));
        pages.add(new AlbumPage(1, false));
        Mockito.when(pagesService.list()).thenReturn(pages);
        
        this.mockMvc = MockMvcBuilders.standaloneSetup(uploadController).build();

    }

    @Test
    public void testPictures() throws Exception {        
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pictures");
       
        this.mockMvc.perform(requestBuilder).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.model().attribute("pictures", pictures)).
                andExpect(MockMvcResultMatchers.model().attribute("pages", pages)).
                andExpect(MockMvcResultMatchers.model().size(3)).
                andExpect(MockMvcResultMatchers.view().name("picturesList"));
    }
    
    @Test
    public void testHome() throws Exception {
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        this.mockMvc.perform(requestBuilder).
                andExpect(MockMvcResultMatchers.status().isFound()).
                andExpect(MockMvcResultMatchers.view().name("redirect:/pictures"));
    }
    
    @Test
    public void testAddNew() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/add");
        this.mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.view().name("addPicture"));
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
        
        this.mockMvc
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(
                MockMvcResultMatchers.view().name("redirect:/pictures"));        

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