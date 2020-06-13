import sun.font.TrueTypeFont;

import java.util.HashMap;
import java.util.Map;



public class Locker {
    private int size;
    private Map<Ticket,Pack> savePack=new HashMap<Ticket,Pack>();

    public Locker(int size){
        this.size=size;
    }

    public Ticket save(Pack pack){
        if (size==0){
            return null;
        }
        Ticket ticket=new Ticket();
        savePack.put(ticket,pack);
        return ticket;
    }
}
