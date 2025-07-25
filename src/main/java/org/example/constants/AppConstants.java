package org.example.constants;

public class AppConstants {
    private AppConstants() {}

    //base
    public static final String BASE_URL = "https://cog-stg.incubatelabs.com/";
    public static final int DEFAULT_TIMEOUT = 10;

    //login page
    public static final String EMAIL_LOGIN = "//input[@id='emailField']";
    public static final String PASSWORD_LOGIN = "//input[@id='pwFiled']";
    public static final String LOGIN_BUTTON = "//button[@id='login-button']";
    public static final String ERROR_MESSAGE = "//div[@class='alert alert-danger' and @role='alert']";

    //sign up page
    public static final String FIRST_NAME_INPUT = "//input[@id=\"fname\"]";
    public static final String LAST_NAME_INPUT = "//input[@id=\"lanme\"]";
    public static final String EMAIL_REGISTER = "//input[@id=\"email\"]";
    public static final String MOBILE_NUMBER = "//input[@id=\"mobileNum\"]";
    public static final String PASSWORD_REGISTER = "//input[@id=\"pw\"]";
    public static final String SIGNUP_BUTTON ="//button[@id=\"submit\"]";

    //buttons
    public static final String HAMBURG = "//button[@id='HeaderLinksDesktopMenu']";
    public static final String LOGIN_BUTTON_FROM_HAM = "//a[@id='HeaderLinksLogin1']";
    public static final String SIGN_UP_BUTTON_FROM_HAM = "//a[@id='HeaderLinksLogin2']";
    public static final String SIGN_UP_WITH_EMAIL = "//a[@id='continue_with_email_signup']";

    //sidebar elements
    public static final String PROFILE_SIDEBAR_XPATH = "//div[@class='n-s-a accordion']";
    public static final String LOGOUT_LINK_XPATH = "//a[@id='SideBarLinkSign']";

}
