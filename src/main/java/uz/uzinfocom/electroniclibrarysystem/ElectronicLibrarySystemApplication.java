package uz.uzinfocom.electroniclibrarysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElectronicLibrarySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicLibrarySystemApplication.class, args);
    }

}
