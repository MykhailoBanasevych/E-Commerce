package pl.mbanasevych.ecommerce.sales.payu;

public class OrderCreateRequest {
    String customerIp;
    String merchantPosId;
    String currencyCode;
    String totalAmount;
    List<Product> products;
    Buyer buyer;
    String extOrderId;
}
