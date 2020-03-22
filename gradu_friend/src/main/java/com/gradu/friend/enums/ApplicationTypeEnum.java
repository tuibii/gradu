package com.gradu.friend.enums;

/**
 * @author wuyiliang
 */
public enum ApplicationTypeEnum {
    /**
     *  json
     */
    JSON("application/json"),
    /**
     *  xml
     */
    XML("application/xml"),
    /**
     *  text
     */
    TEXT("text/xml"),
    /**
     *  form
     */
    FORM("application/x-www-form-urlencoded");

    private String type;

    private ApplicationTypeEnum(String type) {
        this.type = type;
    }

    public String val() {
        return type;
    }

}
