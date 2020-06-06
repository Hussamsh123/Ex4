package com.hussam.ex2postpc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

class Item {
    private String itemText;
    private int compareInt;
    private boolean isDone;
    private String ID;
    private String creationTime;
    private String editTime;

    public Item(){
    }

    public Item(String text, boolean clicks, int id, String creationTime, String editTime){
        itemText = text;
        isDone = clicks;
        ID = UUID.randomUUID().toString();
        this.creationTime = creationTime;
        this.editTime = editTime;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

//    @Override
//    public int compareTo(Item other) {
//        return  0;
//    }

    //    void setIsDone(boolean click){
//        isDone = click;
//    }
//    String getItemText(){
//        return itemText;
//    }
//    boolean isDone(){
//        return isDone;
//    }
//    void setItemText(String newText){itemText = newText;}
//    void editCreationTime(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
//        editTime = simpleDateFormat.format(new Date());
//    }
//    String getCreationTime(){ return creationTime;}
//    String getLastEditTime() {return editTime;}
//    public String getItemID(){return ID;}

}
