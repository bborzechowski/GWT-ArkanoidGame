package com.mycompany.arkanoida.shared;

import java.util.ArrayList;
import java.util.List;

public class GameTim {
    private List<Integer>listt = new ArrayList<Integer>();


    public GameTim() {

        for (int i = 0; i <10; i++) {
            listt.add(i);
        }
    }

    public List<Integer> getListt() {
        return listt;
    }
}
