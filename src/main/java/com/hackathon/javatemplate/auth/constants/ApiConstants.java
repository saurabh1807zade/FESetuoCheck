package com.hackathon.javatemplate.auth.constants;

public class ApiConstants {

    public static final String ROOT_URL_HACK = "/hack";

    public static final String ROOT_URL_SAMPLE = "/sample";

    public static final String AUTH = "/auth";

    public static final String EMPTY = "";

    public static final String METHOD_BY_ID = "/{id}";

    public static final String USER_TYPE = "/{user-type}";

    public static final String LOGIN = "/login";

    public static final String REFRESH_TOKEN = "/refresh-token";

    public static final String REGISTER = "/register";

    public static final String TEST = "/test";

    public static final String USERS = "/users";

    public static final String USERS_BY_ID = USERS + METHOD_BY_ID;

    public static final String ADMINS = "/admins";

    public static final String ADMINS_BY_ID = ADMINS + METHOD_BY_ID;

    public static final String ROOT_URL_AUTH = ROOT_URL_HACK + AUTH;

    public static final String LOGIN_URL = USER_TYPE + LOGIN;

    public static final String REFRESH_TOKEN_URL = USER_TYPE + REFRESH_TOKEN;

    public static final String REGISTER_TOKEN_URL = USER_TYPE + REGISTER;

    public static final String ROOT_URL_TEST = ROOT_URL_HACK + TEST;

}
