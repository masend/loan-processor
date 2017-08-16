package za.co.jumo;

/**
 *
 * @author masekela malematsa
 */
public class Product extends Group {
    
    @Override
    public void insertItem(Loan loan){
        if(map.containsKey(loan.getProduct())){
            map.replace(loan.getProduct(), map.get(loan.getProduct()) + loan.getAmount());
        } else {
            map.put(loan.getProduct(), loan.getAmount());
        }
    }
}
