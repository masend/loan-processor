package za.co.jumo;

/**
 *
 * @author masekela malematsa
 */
public class Network extends Group {
    
    @Override
    public void insertItem(Loan loan){
        if(map.containsKey(loan.getNetwork())){
            map.replace(loan.getNetwork(), map.get(loan.getNetwork()) + loan.getAmount());
        } else {
            map.put(loan.getNetwork(), loan.getAmount());
        }
    }
}
