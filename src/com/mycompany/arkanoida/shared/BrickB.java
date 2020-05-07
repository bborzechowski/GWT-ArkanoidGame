package com.mycompany.arkanoida.shared;

import java.util.List;

public class BrickB extends Position {

    public static final int WIDTH = 70;
    public static final int HEIGHT = 35;

    private int count;
    private List<BrickB> brickBlist;

    public BrickB() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
