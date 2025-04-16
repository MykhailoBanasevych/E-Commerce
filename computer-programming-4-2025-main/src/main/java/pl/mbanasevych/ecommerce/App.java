package pl.mbanasevych.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mbanasevych.ecommerce.catalog.ArrayListProductStorage;
import pl.mbanasevych.ecommerce.catalog.Product;
import pl.mbanasevych.ecommerce.catalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Hello Spring");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyCatalog() {
        ProductCatalog productCatalog = new ProductCatalog(new ArrayListProductStorage());

        productCatalog.addProduct("Nice One 1", "nice product");
        productCatalog.addProduct("Nice One 2", "nice product");
        productCatalog.addProduct("Nice One 3", "nice product");

        return productCatalog;
    }
}
