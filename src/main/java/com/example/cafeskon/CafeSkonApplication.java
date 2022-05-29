package com.example.cafeskon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.cafeskon.model.CafeUser;
import com.example.cafeskon.model.ECategory;
import com.example.cafeskon.model.ERole;
import com.example.cafeskon.model.Product;
import com.example.cafeskon.repository.CafeUserRepository;
import com.example.cafeskon.repository.ProductRepository;

@SpringBootApplication
public class CafeSkonApplication implements CommandLineRunner {

    @Autowired
    ProductRepository repoProducts;
    
    @Autowired
    CafeUserRepository repoUsers;
	
	public static void main(String[] args) {
		SpringApplication.run(CafeSkonApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        if (repoProducts.findAll().isEmpty()) {// si no hay productos en DB..
            repoProducts.saveAll(List.of( // añadimos una lista predefinida!
                new Product("COOKIE", "A chocolate chip cookie with a contagious smile", 5.00, "https://i.imgur.com/5maRHsF.jpg", ECategory.COOKIE),
                new Product("Lemon Cake", "Fluffy piece of cake bursting with fresh lemon flavor", 4.00, "https://i.imgur.com/Sr9DI3v.jpg", ECategory.CAKE),
                new Product("Strawberry Latte", "A fruity drink made of milk, natural strawberry, whipped cream and strawberry syrup", 4.00, "https://i.imgur.com/GE4IkSo.jpg", ECategory.DRINK),
                new Product("Tropical Drink", "", 4.00, "https://i.imgur.com/b8ki3hO.jpg", ECategory.DRINK),
                new Product("Chocolate Snow Cake", "", 3.00, "https://i.imgur.com/F8iobKW.jpg", ECategory.CAKE),
                new Product("Jam Scone", "", 3.00, "https://i.imgur.com/UOIYWTU.jpg", ECategory.CAKE),
                new Product("Choco Scone", "", 3.00, "https://i.imgur.com/u3NHH8x.jpg", ECategory.CAKE),
                new Product("Strawberry Drink", "", 4.00, "https://i.imgur.com/siT1ZJm.jpg", ECategory.DRINK),
                new Product("Moccacino", "", 4.00, "https://i.imgur.com/Ry5ypvd.jpg", ECategory.COFFEE),
                new Product("Iced Capuccino", "", 4.50, "https://i.imgur.com/wzuCPkC.jpg", ECategory.COFFEE),
                new Product("Bear Scone", "", 3.00, "https://i.imgur.com/A7peeJ3.jpg", ECategory.CAKE),
                new Product("Fruit Cake", "", 5.50, "https://i.imgur.com/FtuAtYU.jpg", ECategory.CAKE),
                new Product("Apple Crumble", "", 5.00, "https://i.imgur.com/kwq0N3S.jpg", ECategory.CAKE),
                new Product("Blueberry Crumble", "", 5.00, "https://i.imgur.com/CZ0l1Mo.jpg", ECategory.CAKE),
                new Product("Pack of Chocolate Cookies", "", 6.00, "https://i.imgur.com/RostyWD.jpg", ECategory.COOKIE),
                new Product("Peach Drink", "", 4.00, "https://i.imgur.com/Oz5qkCA.jpg", ECategory.DRINK),
                new Product("Expresso Plus Ice Cream", "", 6.00, "https://i.imgur.com/jSMYtgx.jpg", ECategory.COFFEE),
                new Product("Waffle", "", 4.00, "https://i.imgur.com/UpZs37N.jpg", ECategory.CAKE),
                new Product("Strawberry Cake", "", 4.00, "https://i.imgur.com/Pqz5Qoj.jpg", ECategory.CAKE),
                new Product("Cafe Dalgona", "", 4.00, "https://i.imgur.com/29lZmM2.jpg", ECategory.COFFEE),
                new Product("Bear Cookie", "", 5.00, "https://i.imgur.com/4SUNcxA.jpg", ECategory.COOKIE),
                new Product("Milk Caramel", "", 4.00, "https://i.imgur.com/vNLfibA.jpg", ECategory.COFFEE),
                new Product("Orange Drink", "", 4.00, "https://i.imgur.com/263SD97.jpg", ECategory.DRINK),
                new Product("Lemon Drink", "", 4.00, "https://i.imgur.com/refGhzE.jpg", ECategory.DRINK),
                new Product("Puppy Cupcakes", "", 6.00, "https://i.imgur.com/EsLfzbV.jpg", ECategory.CAKE),
                new Product("Iced Expresso", "", 4.00, "https://i.imgur.com/gruoIMR.jpg", ECategory.COFFEE)
                // ... otros productos
            ));
        }
        if(repoUsers.findAll().isEmpty()) {
        	repoUsers.save(new CafeUser("admin", "admin@cafeskon.com", "admin1234", ERole.ADMIN));
        }
    }
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
