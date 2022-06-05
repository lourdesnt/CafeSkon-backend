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

/**
 * Clase CafeSkonApplication
 * 
 * @author Lourdes Navarro
 *
 */
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
        if (repoProducts.findAll().isEmpty()) { // Si no hay productos en la base de datos...
            repoProducts.saveAll(List.of( 		// ... se añade una lista de productos predefinida
                new Product("COOKIE", "A chocolate chip cookie with a contagious smile", 5.00, "https://i.imgur.com/5maRHsF.jpg", ECategory.COOKIE),
                new Product("Lemon Cake", "Fluffy piece of cake bursting with fresh lemon flavor", 4.00, "https://i.imgur.com/Sr9DI3v.jpg", ECategory.CAKE),
                new Product("Strawberry Latte", "A fruity drink made of milk, natural strawberry, whipped cream and strawberry syrup", 4.00, "https://i.imgur.com/GE4IkSo.jpg", ECategory.DRINK),
                new Product("Tropical Drink", "A delicious peach, pineapple and orange drink", 4.00, "https://i.imgur.com/b8ki3hO.jpg", ECategory.DRINK),
                new Product("Chocolate Snow Cake", "Chocolate cake with powdered sugar on top", 3.00, "https://i.imgur.com/F8iobKW.jpg", ECategory.CAKE),
                new Product("Jam Scone", "Our classic scone with strawberry or peach jam", 3.00, "https://i.imgur.com/UOIYWTU.jpg", ECategory.CAKE),
                new Product("Choco Scone", "Chocolate version of our scone for the choco-lovers", 3.00, "https://i.imgur.com/u3NHH8x.jpg", ECategory.CAKE),
                new Product("Strawberry Drink", "A fresh drink with strawberry flavor", 4.00, "https://i.imgur.com/siT1ZJm.jpg", ECategory.DRINK),
                new Product("Moccacino", "One shot of espresso, two of milk, chocolate and voilà!", 4.00, "https://i.imgur.com/Ry5ypvd.jpg", ECategory.COFFEE),
                new Product("Iced Cappuccino", "A cold cappuccino perfect for hotter days", 4.50, "https://i.imgur.com/wzuCPkC.jpg", ECategory.COFFEE),
                new Product("Bear Scone", "A scone filled with pistachio and flowers and bears mini-cookies", 3.00, "https://i.imgur.com/A7peeJ3.jpg", ECategory.CAKE),
                new Product("Fruit Cake", "A complete fruit cake with bananas, kiwi, berries and melon", 5.50, "https://i.imgur.com/FtuAtYU.jpg", ECategory.CAKE),
                new Product("Apple Crumble", "An very soft apple cake", 5.00, "https://i.imgur.com/kwq0N3S.jpg", ECategory.CAKE),
                new Product("Blueberry Crumble", "A very soft blueberry cake", 5.00, "https://i.imgur.com/CZ0l1Mo.jpg", ECategory.CAKE),
                new Product("Pack of Chocolate Cookies", "A pack of 4 Snoppy-inspired chocolate cookies", 6.00, "https://i.imgur.com/RostyWD.jpg", ECategory.COOKIE),
                new Product("Peach Drink", "A fresh drink with peach flavor", 4.00, "https://i.imgur.com/Oz5qkCA.jpg", ECategory.DRINK),
                new Product("Espresso Plus Ice Cream", "You can now enjoy our espresso with vanilla ice cream", 6.00, "https://i.imgur.com/jSMYtgx.jpg", ECategory.COFFEE),
                new Product("Waffle", "A waffle with chocolate or caramel syrup, strawberries and ice cream", 4.00, "https://i.imgur.com/UpZs37N.jpg", ECategory.CAKE),
                new Product("Strawberry Cake", "A cake made of cream and natural strawberries", 4.00, "https://i.imgur.com/Pqz5Qoj.jpg", ECategory.CAKE),
                new Product("Cafe Dalgona", "A korean specialty, creamy coffee with whipped cream", 4.00, "https://i.imgur.com/29lZmM2.jpg", ECategory.COFFEE),
                new Product("Bear Cookie", "An adorable chocolate bear cookie", 5.00, "https://i.imgur.com/4SUNcxA.jpg", ECategory.COOKIE),
                new Product("Milk Caramel", "Coffe with lot of caramel", 4.00, "https://i.imgur.com/vNLfibA.jpg", ECategory.COFFEE),
                new Product("Orange Drink", "A fresh drink with orange flavor", 4.00, "https://i.imgur.com/263SD97.jpg", ECategory.DRINK),
                new Product("Lemon Drink", "A lemon drink with lemon flavor", 4.00, "https://i.imgur.com/refGhzE.jpg", ECategory.DRINK),
                new Product("Puppy Cupcakes", "Mini vanilla cupcakes with puppy shape", 6.00, "https://i.imgur.com/EsLfzbV.jpg", ECategory.CAKE),
                new Product("Iced Espresso", "A cold espresso perfect for hotter days", 4.00, "https://i.imgur.com/gruoIMR.jpg", ECategory.COFFEE)
                // ...
            ));
        }
        if(repoUsers.findAll().isEmpty()) { //Si no hay usuarios en la base de datos...
        	repoUsers.save(new CafeUser("admin", "admin@cafeskon.com", "admin1234", ERole.ADMIN)); //... se añade al admin
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
