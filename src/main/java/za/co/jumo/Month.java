package za.co.jumo;

/**
 *
 * @author masekela malematsa
 */
public class Month extends Group  {
    
    @Override
    public void insertItem(Loan loan){
        if(map.containsKey(loan.getMonth())){
            map.replace(loan.getMonth(), map.get(loan.getMonth()) + loan.getAmount());
        } else {
            map.put(loan.getMonth(), loan.getAmount());
        }
    }
}
