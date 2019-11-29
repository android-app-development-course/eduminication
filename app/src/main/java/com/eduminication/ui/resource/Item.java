package com.eduminication.ui.resource;

import android.widget.TextView;

public class Item {
    private String name;
    private String course1, course2, course3, course4, course5;

    protected Item(String name, String course1, String course2, String course3, String course4, String course5){
        this.name = name;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
    }

    public String getName(){
        return name;
    }

    public String getCourse1(){
        return course1;
    }

    public String getCourse2(){
        return course2;
    }

    public String getCourse3(){
        return course3;
    }

    public String getCourse4(){
        return course4;
    }

    public String getCourse5(){
        return course5;
    }
}
