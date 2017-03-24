/**
 * 
 * */
package com.bobko.storage.common;

public enum UserRolesTypes {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER") {
        @Override
        public String getValue() {
            return "ROLE_USER";
        }
    },
    ROLE_ANONYMOUS("ROLE_ANONYMOUS");
    
    private String value;
    
    private UserRolesTypes(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
}
