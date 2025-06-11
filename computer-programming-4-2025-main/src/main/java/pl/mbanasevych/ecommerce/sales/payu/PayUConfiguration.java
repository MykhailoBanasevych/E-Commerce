package pl.mbanasevych.ecommerce.sales.payu;

public class PayUConfiguration {
    String posId;
    String md5;
    String clientId;
    String clientSecret;
    boolean sandboxMode;

    public PayUConfiguration(String posId, String md5, String clientId, String clientSecret, Boolean sandboxMode){
        this.posId = posId;
        this.md5 = md5;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.sandboxMode = sandboxMode;
    }

    public static PayUConfiguration byEnvVariables() {
        return new PayUConfiguration(
                System.getenv("PAYU_POS_ID"),
                System.getenv("PAYU_MD5"),
                System.getenv("PAYU_CLIENT_ID"),
                System.getenv("PAYU_CLIENT_SECRET"),
                sandboxMode: false);
        )
    }

    public static PayUConfiguration sandbox(){
        return new PayUConfiguration(
                posId: "300746",
                md5: "b6ca15b0d1020e8094d9b5f8d163db54",
    }
}
