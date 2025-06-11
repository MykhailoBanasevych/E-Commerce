package pl.mbanasevych.ecommerce.sales.payu;
import org.junit.jupiter.api.Test;

public class PayUTest {
    @Test
    void itRegisterPayment(){
        PayU payU = thereIsPayU();

        OrderCreateRequest request = thereisExampleOrderCreateRequest();
        OrderCreateRespone response = payU.handle(request);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getorderId());


    }

    private OrderCreateRequest thereisExampleOrderCreateRequest() {
        return null;
    }

    private OrderCreateRequest thereIsExmpleOrderCreateRequest(){
        var exampleOrderCreateRequest = new OrderCreateRequest();
        exampleOrderCreateRequest
                .setCustomerIp("127.0.0.1")
                .setDescription("Some service")
                .setCurrencyCode("PLN")
                .setTotalAmount("21000")
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                        .setEmail("john.doe@example.com")
                        .setFirstName("john")
                        .setLastName("doe")
                )
                .setProducts(Array.asList(
                        new Product()
                                .setName("My ebook 1")
                                .set

    }
}
