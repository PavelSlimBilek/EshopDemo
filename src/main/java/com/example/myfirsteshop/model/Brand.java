package com.example.myfirsteshop.model;

import java.util.ArrayList;
import java.util.List;

public enum Brand {
    GIBSON, JACKSON, LTD, MESA, ENGL, MARSHALL, BOSS, MAXON, HUGHESKETTNER, PEAVEY;

    public static List<String> getBrands() {
        List<String> record = new ArrayList<>();
        for (Brand b : Brand.values()) {
            record.add(b.toString());
        }
        return record;
    }
}
