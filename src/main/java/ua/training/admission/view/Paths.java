package ua.training.admission.view;

/**
 * Paths
 */
public final class Paths {

    public static final String REDIRECTED = "REDIRECTED";

    /* Paths for Commands */
    public static final String HOME = "/home";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String SIGNUP = "/signup";
    public static final String USERS = "/users";
    public static final String USERS_ = USERS + "/";
    public static final String USERS_SELECT_SPEC = "/selectSpec";
    public static final String USERS_GRADES = "/grades";

    /* api */
    public static final String API = "/api";
    public static final String API_LOGIN = API + LOGIN;
    public static final String API_LOGIN_ERROR = API_LOGIN + "?error=true";
    public static final String API_LOGIN_LOGOUT = API_LOGIN + "?logout=true";
    public static final String API_LOGIN_AUTHORIZED = API_LOGIN + "?authorized=true";
    public static final String API_HOME = API + HOME;

    /* JSP */
    private static final String PREFIX = "/WEB-INF/view";
    public static final String HOME_JSP = PREFIX + "/home.jsp";
    public static final String LOGIN_JSP = PREFIX + "/login.jsp";
    public static final String SIGNUP_JSP = PREFIX + "/signup.jsp";
    public static final String USERLIST_JSP = PREFIX + "/userList.jsp";
    public static final String USER_SPECIALITY_JSP = "/userSpeciality.jsp";
    public static final String PAGE_404 = PREFIX + "/404.jsp";
    public static final String USER_GRADES_JSP = "/userGrades.jsp";
}
