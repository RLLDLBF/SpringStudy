package com.example.springweb.dao;

import java.util.List;

public class ClassName {
    private String name;
    private String type;
    private String packageName;
    private String methods;
    private int metNum;
    private String eachMethod[];

    public String[] getEachMethod() {
        return eachMethod;
    }

    public void setEachMethod(String[] eachMethod) {
        this.eachMethod = eachMethod;
    }

    public void methodsTeach(){
        if(methods!=null){
            String temp=this.methods;
            eachMethod=temp.split("/n");
        }
    }

    public int getMetNum() {
        return metNum;
    }

    public void setMetNum(int metNum) {
        this.metNum = metNum;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getType() {
        return type;
    }

    public String getMethods() {
        return methods;
    }
}
