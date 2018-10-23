package com.tl.demo.springboot.enums;

public enum  JasyptAlgorithmEnum {

    PBEWithMD5AndDES("PBEWithMD5AndDES");

    private String algorithmName;

    JasyptAlgorithmEnum(String algorithmName){
        this.algorithmName = algorithmName;
    }

    public String toString(){
        return this.algorithmName;
    }
}
