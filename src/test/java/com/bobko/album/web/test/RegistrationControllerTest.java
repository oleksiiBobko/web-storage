package com.bobko.album.web.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;










import com.bobko.album.domain.*;
import com.bobko.album.web.*;
import com.bobko.album.service.*;
import com.bobko.album.service.interfaces.IUserService;

import org.mockito.*; 
import org.junit.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:META-INF/test-context.xml", "classpath:META-INF/application-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class RegistrationControllerTest {
   
    private Users newUser;
    private Users existsUser;
    
    @Autowired
    private WebApplicationContext wac;
   
    private MockMvc mockMvc;

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private IUserService userService;
   
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        existsUser = new Users();
        existsUser.setUsrName("alex");
        existsUser.setActive(true);
        existsUser.setRole(IUserService.ROLE_ADMIN);
        existsUser.setPw("1111");
        
        Mockito.when(userService.getUser("alex")).thenReturn(newUser);
        this.mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void testCreateNewUser() throws Exception {

        MockHttpServletRequestBuilder requBuilder = MockMvcRequestBuilders.post("/adduser").
                param("pw", "1111").
                param("usrName", "alex");
       
        this.mockMvc.perform(requBuilder).
                andExpect(MockMvcResultMatchers.status().isFound()).
                andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }

    @Test
    public void testRegistrationPage() throws Exception {        
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/registration");
       
        this.mockMvc.perform(requestBuilder).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.model().size(1)).
                andExpect(MockMvcResultMatchers.view().name("registrationPage"));
    }    
    
}