package com.skyeyeh.mongo.model;

import org.bson.Document;

/**
 * 安控交易功能。
 */
public class SaabPrivilegeData {
    public Document document;
    public final static String APP_ID = "app_id";
    public final static String PRIVILEGE_ID = "privilege_id";
    public final static String C_NAME = "c_name";
    public final static String E_NAME = "e_name";
    public final static String STATUS = "status";
    public final static String CATEGORY = "category";
    public final static String DESCRIPTION = "description";
    public final static String BUILD_DATE = "build_date";
    public final static String TYPE = "type";
    public final static String AUTH_METHOD = "auth_method";
    public final static String PARENT_ID = "parent_id";


    public final static char TYPE_BUTTON = 'B';
    public final static char TYPE_DATA = 'A';
    public final static char TYPE_MENU = 'M';
    public final static char TYPE_PRIVILEGE = 'P';
    public final static String STATUS_ACTIVE = "A";
    public final static String STATUS_DISABLE = "D";

    /**
     * 初始化交易功能。
     *
     * @param app_id       系統代碼
     * @param privilege_id 交易功能代碼
     * @param c_name       中文名稱
     * @param type         交易功能種類
     */
    public SaabPrivilegeData(String app_id, String privilege_id, String c_name, char type) {
        document = new Document();
        setApp_id(app_id);
        setPrivilege_id(privilege_id);
        setC_name(c_name);
        setType(type);
        setStatus(STATUS_ACTIVE);
    }

    public String getApp_id() {
        return document.get(APP_ID).toString();
    }

    public void setApp_id(String app_id) {
        document.append(APP_ID, app_id);
    }

    public String getPrivilege_id() {
        return document.get(PRIVILEGE_ID).toString();
    }

    public void setPrivilege_id(String privilege_id) {
        document.append(PRIVILEGE_ID, privilege_id);
    }

    public String getC_name() {
        return document.get(C_NAME).toString();
    }

    public void setC_name(String c_name) {
        document.append(C_NAME, c_name);
    }

    public String getE_name() {
        return document.get(E_NAME).toString();
    }

    public void setE_name(String e_name) {
        document.append(E_NAME, e_name);
    }

    public String getStatus() {
        return document.get(STATUS).toString();
    }

    public void setStatus(String status) {
        document.append(STATUS, status);
    }

    public String getCategory() {
        return document.get(CATEGORY).toString();
    }

    public void setCategory(String category) {
        document.append(CATEGORY, category);
    }

    public String getDescription() {
        return document.get(DESCRIPTION).toString();
    }

    public void setDescription(String description) {
        document.append(DESCRIPTION, description);
    }

    public String getBuild_date() {
        return document.get(BUILD_DATE).toString();
    }

    public void setBuild_date(String build_date) {
        document.append(BUILD_DATE, build_date);
    }

    public char getType() {
        return (char) document.get(TYPE);
    }

    public void setType(char type) {
        document.append(TYPE, type);
    }

    public String getAuth_method() {
        return document.get(AUTH_METHOD).toString();
    }

    public void setAuth_method(String auth_method) {
        document.append(AUTH_METHOD, auth_method);
    }

    public String getParent_id() {
        return document.get(PARENT_ID).toString();
    }

    public void setParent_id(String parent_id) {
        document.append(PARENT_ID, parent_id);
    }
}
