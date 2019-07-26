package ua.training.admission.view;

/**
 * Paths
 */
public final class Paths {

    public static final String REDIRECTED = "REDIRECTED";

    /* api */
    public static final String API = "/api";

    /* Paths for Commands */
    // GET
    public static final String HOME = API + "/home";
    public static final String LOGIN = API + "/login";
    public static final String LOGOUT = API + "/logout";
    public static final String SIGNUP = API + "/signup";
    public static final String USERS = API + "/users";
    public static final String USERS_ = USERS + "/";
    public static final String GRADES = "/grades";
    public static final String USERS_GRADES = API + GRADES;
    public static final String SPEC = "/spec";
    public static final String USERS_SELECT_SPEC = API + SPEC;
    // POST
    public static final String USERS_UPDATE_SPEC = USERS + "/updateSpec";
    public static final String USERS_UPDATE_GRADES = USERS + "/updateGrades";
    // ERRORS
    public static final String LOGIN_ERROR = LOGIN + "?error=true";
    public static final String LOGIN_LOGOUT = LOGIN + "?logout=true";
    public static final String LOGIN_AUTHORIZED = LOGIN + "?authorized=true";

    /* JSP */
    private static final String PREFIX = "/WEB-INF/view";
    public static final String HOME_JSP = PREFIX + "/home.jsp";
    public static final String LOGIN_JSP = PREFIX + "/login.jsp";
    public static final String SIGNUP_JSP = PREFIX + "/signup.jsp";
    public static final String USERLIST_JSP = PREFIX + "/userList.jsp";
    public static final String USER_SPECIALITY_JSP = PREFIX + "/userSpeciality.jsp";
    public static final String USER_GRADES_JSP = PREFIX + "/userGrades.jsp";
    public static final String PAGE_404_JSP = PREFIX + "/404.jsp";
}
