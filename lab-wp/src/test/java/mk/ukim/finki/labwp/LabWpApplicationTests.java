package mk.ukim.finki.labwp;

import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Manufacturer;
import mk.ukim.finki.labwp.model.Role;
import mk.ukim.finki.labwp.service.BalloonService;
import mk.ukim.finki.labwp.service.ManufacturerService;
import mk.ukim.finki.labwp.service.UserService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabWpApplicationTests {

    MockMvc mockMvc;


    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;




    @Autowired
    BalloonService balloonService;

    private static Balloon c1;
    private static Manufacturer m1;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }
    private void initData() {
        if (!dataInitialized) {


            m1 = manufacturerService.save("m1", "m1","m1").get();
            manufacturerService.save("m2", "m2","m2");

            String user = "user";
            String admin = "admin";

            userService.register(user, user, user,  Role.ROLE_USER);
            userService.register(admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }


    @Test
    void contextLoads() {
    }

    @Test
    public void testGetBalloons() throws Exception {
        MockHttpServletRequestBuilder balloonRequest = MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(balloonRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "listBalloons"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));

    }
   


}
