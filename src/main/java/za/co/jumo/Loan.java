package za.co.jumo;

/**
 *
 * @author masekela malematsa
 */
public class Loan {

    private String msisdn;
    private String product;
    private String network;
    private String month;
    private Double amount;

    public Loan() {

    }

    public Loan(String msisdn, String product, String network, String month, Double amount) {
        this.msisdn = msisdn;
        this.product = product;
        this.network = network;
        this.month = month;
        this.amount = amount;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Loan{" + "msisdn=" + msisdn + ", product=" + product + ", network=" 
                + network + ", month=" + month + ", amount=" + amount + "'}'";
    }
}
