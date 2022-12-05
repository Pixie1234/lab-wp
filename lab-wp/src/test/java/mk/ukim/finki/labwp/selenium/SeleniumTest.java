package mk.ukim.finki.labwp.selenium;


import mk.ukim.finki.labwp.model.Manufacturer;
import mk.ukim.finki.labwp.model.Role;
import mk.ukim.finki.labwp.model.User;
import mk.ukim.finki.labwp.service.BalloonService;
import mk.ukim.finki.labwp.service.ManufacturerService;
import mk.ukim.finki.labwp.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumTest {


    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;


    @Autowired
    BalloonService balloonService;


    private HtmlUnitDriver driver;


    private static Manufacturer m1;
    private static Manufacturer m2;

    private static User adminUser;


    private static String admin = "admin";

    private static boolean dataInitialized = false;



    @BeforeEach
    private void setup(){
        this.driver=new HtmlUnitDriver(true);
        initData();
    }


    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {


            m1 = manufacturerService.save("m1", "m1","m1").get();
            m2 = manufacturerService.save("m2", "m2","m2").get();


            //regularUser = userService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin,  Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElemts(0, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        balloonsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);

        balloonsPage.assertElemts(0, 0, 0, 1);

        balloonsPage = AddOrEditBalloon.addBalloon(this.driver, "red", "small", m2.getName());
        balloonsPage.assertElemts(1, 1, 1, 1);

        balloonsPage= AddOrEditBalloon.addBalloon(this.driver, "blue", "large", m2.getName());
        balloonsPage.assertElemts(2, 2, 2, 2);

        balloonsPage.getDeleteButtons().get(1).click();
        balloonsPage.assertElemts(1, 1, 1, 1);

    balloonsPage = AddOrEditBalloon.editBalloon(this.driver, balloonsPage.getEditButtons().get(0), "red", "small",  m2.getName());
        balloonsPage.assertElemts(1, 1, 1, 1);




    }

}

