package com.example.please;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private List<String> codeList = new ArrayList<>();

    private boolean hasCode1;
    private boolean hasCode2;
    private boolean hasCode3;
    private boolean hasCode4;
    private boolean hasCode5;
    private boolean hasCode6;
    private boolean hasCode7;
    private boolean hasCode8;

    private boolean hasTicket;
    private boolean hasWon;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeCodeList();
        hasCode1 = false;
        hasCode2 = false;
        hasCode3 = false;
        hasCode4 = false;
        hasCode5 = false;
        hasCode6 = false;
        hasCode7 = false;
        hasCode8 = false;
        hasTicket = false;
        hasWon = false;
    }

    private void initializeCodeList() {
        codeList.add("a");
        codeList.add("b");
        codeList.add("c");
        codeList.add("d");
        codeList.add("e");
        codeList.add("f");
        codeList.add("g");
        codeList.add("h");
        codeList.add("i");
        codeList.add("j");
        codeList.add("k");
        codeList.add("l");
    }

    public int codeListLenght() {
        int codeListLenght = codeList.size();
        return codeListLenght;
    }

    public String getListElement(int index) {
        String elementIndex = codeList.get(index);
        return elementIndex;
    }

    public void removeListElement(String element) {
        codeList.remove(element);
    }

    public boolean getHasCode1() {
        return hasCode1;
    }
    public void setHasCode1(boolean hasCode1) {
        this.hasCode1 = hasCode1;
    }
    public boolean getHasCode2() {
        return hasCode2;
    }
    public void setHasCode2(boolean hasCode2) {
        this.hasCode2 = hasCode2;
    }
    public boolean getHasCode3() {
        return hasCode3;
    }
    public void setHasCode3(boolean hasCode3) {
        this.hasCode3 = hasCode3;
    }
    public boolean getHasCode4() {
        return hasCode4;
    }
    public void setHasCode4(boolean hasCode4) {
        this.hasCode4 = hasCode4;
    }
    public boolean getHasCode5() {
        return hasCode5;
    }

    public void setHasCode5(boolean hasCode5) {
        this.hasCode5 = hasCode5;
    }

    public boolean getHasCode6() {
        return hasCode6;
    }

    public void setHasCode6(boolean hasCode6) {
        this.hasCode6 = hasCode6;
    }

    public boolean getHasCode7() {
        return hasCode7;
    }

    public void setHasCode7(boolean hasCode7) {
        this.hasCode7 = hasCode7;
    }

    public boolean getHasCode8() {
        return hasCode8;
    }

    public void setHasCode8(boolean hasCode8) {
        this.hasCode8 = hasCode8;
    }

    public boolean getHasTicket() {
        return hasTicket;
    }
    public void setHasTicket(boolean hasTicket) {
        this.hasTicket = hasTicket;
    }

    public boolean getHasWon() {
        return hasWon;
    }
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

}
