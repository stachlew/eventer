package pl.wat.api.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by przem on 19.05.2017.
 */
public class Month {
    private static final Map<Integer, String> miesiaceMap;
    private static final String[] miesiace = { "Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien", "Pazdziernik", "Listopad", "Grudzien" };
    static {
        miesiaceMap = new HashMap<>();
        int i = 1;
        for(String miesiac : miesiace) {
            miesiaceMap.put(i, miesiac);
            i++;
        }
    }

    public static String parseMiesiac(BigDecimal numer) {
        System.out.println(Month.miesiaceMap.get(numer.intValue()));
        return  Month.miesiaceMap.get(numer.intValue());
    }
}
