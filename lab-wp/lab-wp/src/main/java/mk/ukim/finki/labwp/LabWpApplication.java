package mk.ukim.finki.labwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class LabWpApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabWpApplication.class, args);
    }

}
