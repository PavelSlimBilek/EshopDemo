package com.example.myfirsteshop.model;

import java.util.ArrayList;
import java.util.List;

public enum ProdType {
    GUITAR, AMP, CABINET, PEDAL;

    public static List<String> getTypes() {
        List<String> record = new ArrayList<>();
        for (ProdType t : ProdType.values()) {
            record.add(t.toString());
        }
        return record;
    }
}
