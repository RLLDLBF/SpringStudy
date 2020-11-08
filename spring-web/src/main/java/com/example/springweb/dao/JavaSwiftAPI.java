package com.example.springweb.dao;

import java.io.Serializable;


public class JavaSwiftAPI implements Serializable
{
    private int apiId;
    private String apiNameForSearch;
    private String fullName;
    private String packageName;
    private String className;
    private String methodName ;
    private String apiSignature;
    private String description;
    private String formParameter;
    private String returnParam;
    private String codeSample;
    public JavaSwiftAPI(){
        apiId=0;
        apiNameForSearch=null;
        fullName=null;
        packageName=null;
        className=null;
        methodName=null;
        apiSignature=null;
        description=null;
        formParameter=null;
        returnParam=null;
        codeSample =null;
    }
    public JavaSwiftAPI(int apiId,String apiName,String fullName,String packageName,String className,String methodName,String apiSignature,String description,String formParameter,String returnParam,String codeSample){
        this.apiId=apiId;
        this.apiNameForSearch=apiName;
        this.fullName=fullName;
        this.packageName=packageName;
        this.className=className;
        this.methodName=methodName;
        this.apiSignature=apiSignature;
        this.description=description;
        this.formParameter=formParameter;
        this.returnParam=returnParam;
        this.codeSample = codeSample;
    }

    public int getApiId() {
        return apiId;
    }

    public String getApiNameForSearch() {
        return apiNameForSearch;
    }

    public String getFullName() {
        return fullName;
    }

    public String getApiSignature() {
        return apiSignature;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getCodeSample() {
        return codeSample;
    }

    public String getDescription() {
        return description;
    }

    public String getFormParameter() {
        return formParameter;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getReturnParam() {
        return returnParam;
    }

    public void setApiNameForSearch(String apiNameForSearch) {
        this.apiNameForSearch = apiNameForSearch;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setApiSignature(String apiSignature) {
        this.apiSignature = apiSignature;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCodeSample(String codeSample) {
        this.codeSample = codeSample;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFormParameter(String formParameter) {
        this.formParameter = formParameter;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setReturnParam(String returnParam) {
        this.returnParam = returnParam;
    }
}
