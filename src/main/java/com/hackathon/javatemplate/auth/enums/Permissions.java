package com.hackathon.javatemplate.auth.enums;

import static com.hackathon.javatemplate.auth.enums.groups.PermissionGroups.*;

public enum Permissions {

    USERS(PermissionConstants.USERS, USER.name, "", "Every user default permission."),

    //User
    USER_READ(PermissionConstants.USER_READ, USER.name, "", "Permission to read any User."),
    USER_READ_SELF(PermissionConstants.USER_READ_SELF, USER.name, "", "User permission to read self."),
    USER_CREATE(PermissionConstants.USER_CREATE, USER.name, "", "Permission to add new User."),
    USER_UPDATE(PermissionConstants.USER_UPDATE, USER.name, "", "Permission to update any User."),
    USER_UPDATE_SELF(PermissionConstants.USER_UPDATE_SELF, USER.name, "", "User permission to update self."),
    USER_DELETE(PermissionConstants.USER_DELETE, USER.name, "", "Permission to delete any User."),
    USER_DELETE_SELF(PermissionConstants.USER_DELETE_SELF, USER.name, "", "User permission to delete self."),

    //Admin
    ADMIN_READ(PermissionConstants.ADMIN_READ, ADMIN.name, "", "Permission to read any Admin."),
    ADMIN_READ_SELF(PermissionConstants.ADMIN_READ_SELF, ADMIN.name, "", "Admin permission to read self."),
    ADMIN_CREATE(PermissionConstants.ADMIN_CREATE, ADMIN.name, "", "Permission to add new Admin."),
    ADMIN_UPDATE(PermissionConstants.ADMIN_UPDATE, ADMIN.name, "", "Permission to update any Admin."),
    ADMIN_UPDATE_SELF(PermissionConstants.ADMIN_UPDATE_SELF, ADMIN.name, "", "Admin permission to update self."),
    ADMIN_DELETE(PermissionConstants.ADMIN_DELETE, ADMIN.name, "", "Permission to delete any Admin."),
    ADMIN_DELETE_SELF(PermissionConstants.ADMIN_DELETE_SELF, ADMIN.name, "", "Admin permission to delete self.");


    public final String name;

    public final String error;

    public final String group;

    public final String description;

    Permissions(String name, String group, String error, String description) {
        this.name = name;
        this.group = group;
        this.error = error;
        this.description = description;
    }

    public static class PermissionConstants {
        public static final String USERS = "USERS";
        public static final String USER_READ = "USER_READ";
        public static final String USER_READ_SELF = "USER_READ_SELF";
        public static final String USER_CREATE = "USER_CREATE";
        public static final String USER_UPDATE = "USER_UPDATE";
        public static final String USER_UPDATE_SELF = "USER_UPDATE_SELF";
        public static final String USER_DELETE = "USER_DELETE";
        public static final String USER_DELETE_SELF = "USER_DELETE_SELF";

        public static final String ADMIN_READ = "ADMIN_READ";
        public static final String ADMIN_READ_SELF = "ADMIN_READ_SELF";
        public static final String ADMIN_CREATE = "ADMIN_CREATE";
        public static final String ADMIN_UPDATE = "ADMIN_UPDATE";
        public static final String ADMIN_UPDATE_SELF = "ADMIN_UPDATE_SELF";
        public static final String ADMIN_DELETE = "ADMIN_DELETE";
        public static final String ADMIN_DELETE_SELF = "ADMIN_DELETE_SELF";
    }

}
