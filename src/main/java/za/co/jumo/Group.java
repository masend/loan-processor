package za.co.jumo;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author masekela malematsa
 */
public abstract class Group {
    
    Map<String, Double> map = new TreeMap<>();
    
    public abstract void insertItem(Loan loan);
    
    public Map<String, Double> getMap() {
        return map;
    }

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }
}
