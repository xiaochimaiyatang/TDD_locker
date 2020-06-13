import sun.font.TrueTypeFont;

import static java.lang.Boolean.TRUE;

public class Locker {
    public static void main(String[] args) {

    }

    public static Boolean save_bag(int TotalBox,int RemainBoxNum) {
        Boolean SaveResult= TRUE;
        if (RemainBoxNum>0){
            TotalBox-=1;
        }
        return SaveResult;
    }
}
