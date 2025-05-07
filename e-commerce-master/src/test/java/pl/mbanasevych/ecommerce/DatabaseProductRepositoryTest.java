package pl.mbanasevych.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.mbanasevych.ecommerce.productcatalog.ArrayListProductRepository;
import pl.mbanasevych.ecommerce.productcatalog.Product;
import pl.mbanasevych.ecommerce.productcatalog.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class DatabaseProductRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @BeforeEach
    void setupDatabase(){
        jdbcTemplate.execute("DROP TABLE 'product_catalog__products' IF EXISTS");
        var sql = """
                Create table 'product_catalog__products'(
                    id VARCHAR(100) NOT NULL,
                    name VARCHAR(50) NOT NULL,
                    description VARCHAR(50) NOT NULL,
                    cover VARCHAR(50) NOT NULL,
                    price DECIMAL(12,2),
                    PRIMARY KEY(id)
                    );
                """;
        jdbcTemplate.execute(sql);
    }

    @Test
    void itQueryDatabase() {
        var sql = "select now () curr_time";
        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2025");
    }

    @Test
    void itCreatesTables(){
        var sql = """
                Create table 'product_catalog__products'(
                    id VARCHAR(100) NOT NULL,
                    name VARCHAR(50) NOT NULL,
                    description VARCHAR(50) NOT NULL,
                    cover VARCHAR(50) NOT NULL,
                    price DECIMAL(12,2),
                    PRIMARY KEY(id)
                    );
                """;
        jdbcTemplate.execute(sql);

        var result = jdbcTemplate.queryForObject(
                "select count(*) from 'product_catalog__products", Integer.class);
        assert result == 0;
    }
    @Test
    void insertSomeProductV1(){
        var sql = """
    INSERT INTO 'product_catalog__products (id, name, description)
    VALUES
    ('c0883b61-9a35-47d7-87d4-5ae146e3fdef', 'Nice product 1', 'nice one'),
    ('9ca3c292-ab62-42a8-8da7-dd1fd689803e', 'Niece product 2', 'even nicer');
    """;
   }
    @Test
    void itStoresAndLoadsProducts() {
        Product product = thereIsProduct();
        ProductRepository repository = thereIsRepository();

        repository.save(product);

        Product loaded = repository.loadProductById(product.getId());

        assertEquals(product.getId(), loaded.getId());
    }

    private ProductRepository thereIsRepository() {
        return new ArrayListProductRepository();
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID(), "dfsh", "fdhg");
    }

    @Test
    void insertSomeProductV2DynamicValues(){
        var sql = """
                INSERT INTO 'product_catalog__products(id, name, description)
                VALUES
                    (:id, :name, :desc)'
                """;

        Map<String, Object> params = new HashMap<>();
        params.put("id", "c5cb10db-de75-4c1c-a40d-d615807ad379"); params.put("name", "Product Y");
        params.put("desc", "Nice product Y");
        
        var namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbcTemplate.update(sql, params);

        jdbcTemplate.update(sql, "8b6b4839-f7ea-42ff-8ebb-063b25c26b58", "product X", "nice product X");

        var result = jdbcTemplate.queryForObject(
                "select count(*) from 'product_catalog__products", Integer.class);
        assert result == 0;
    }
}
