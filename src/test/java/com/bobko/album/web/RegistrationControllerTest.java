//package com.bobko.album.web;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.bobko.album.domain.Users;
//import com.bobko.album.service.interfaces.IPictureService;
//import com.bobko.album.service.interfaces.IUserService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-context.xml", "classpath:application-context.xml"})
//@WebAppConfiguration
//public class RegistrationControllerTest {
//       
//    @Mock
//    private Users newUser;
//    
//    @Mock
//    private Users existsUser;
//    
//    @Mock
//    private IUserService userService;
//    
//    @Mock
//    private IPictureService picService;
//    
//    @InjectMocks
//    private RegistrationController registrationController;
//    
//    @Autowired
//    private WebApplicationContext wac;
//   
//    private MockMvc mockMvc;
//
//    private static final String SECRET = "1111";
//
//    private static final String LOGIN = "alex";
//   
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        
//        when(existsUser.getLogin()).thenReturn(LOGIN);
//        when(existsUser.isActive()).thenReturn(Boolean.TRUE);
//        when(existsUser.getRole()).thenReturn(IUserService.ROLE_ADMIN);
//        when(existsUser.getPw()).thenReturn(SECRET);
//        
//        when(userService.getUser("alex")).thenReturn(newUser);
//        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
//    }
//
////    @Test
////    public void getHomeTest() {
////        try {
////            mockMvc.perform(get("/"))
////            .andExpect(status().isOk());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//    
//    @Test
//    public void testCreateNewUser() throws Exception {
//
//        MockHttpServletRequestBuilder requBuilder = MockMvcRequestBuilders.post("/adduser").
//                param("pw", "1111").
//                param("login", "alex");
//        mockMvc.perform(requBuilder).
//                andExpect(status().isFound()).
//                andExpect(view().name("redirect:/login"));
//    }
//
//    @Test
//    public void testRegistrationPage() throws Exception {        
//        
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/registration");
//       
//        mockMvc.perform(requestBuilder).
//                andExpect(status().isOk()).
//                andExpect(model().size(1)).
//                andExpect(view().name("registrationPage"));
//    }    
//    
//}