package com.mycompany.arkanoida.shared;

import java.util.List;

public class BrickR extends Position {

    public static final int WIDTH = 70;
    public static final int HEIGHT = 35;

    private int count;
    private List<BrickR> brickRList;

    public BrickR() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
