package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

   /*
    public ApplicationRunner demo(RestaurantDAO cr) {
        return args -> {
            Restaurant r1 = new Restaurant(1,"Restaurant1", null);
            Menu menu = new Menu(1, "name1", r1);
            Menu[] menus = {menu};
            r1.setMenus(new LinkedList<>(Arrays.asList(menus)));
            cr.save(r1);
            cr.save(new Restaurant(2, "Restauant2", null));
        };
    } */
}
