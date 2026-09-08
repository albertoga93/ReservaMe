package org.alberto.reservame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ReservaMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservaMeApplication.class, args);
    }


    /*

    metodo para generar contraseñas encriptadas
    public static void main (String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("password123"));
      }
     */
}
