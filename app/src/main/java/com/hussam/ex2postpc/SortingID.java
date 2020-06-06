package com.hussam.ex2postpc;

public class SortingID {
    private int nextID;

    public int getNextID() {
        return nextID;
    }

    public void setNextID(int nextID) {
        this.nextID = nextID;
    }

    SortingID(){}
    SortingID(int counter){ nextID = counter;}

    void increment(){++nextID;}
    String returnID(){
        return "0";
    }
}
