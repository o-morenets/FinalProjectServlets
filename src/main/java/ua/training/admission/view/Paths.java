package ua.training.admission.view;

/**
 * Command Paths
 *
 * @author Oleksii Morenets
 */
public final class Paths {

    /* Redirected flag */
    public static final String REDIRECTED = "REDIRECTED";

    /* Servlet Path */
    public static final String SERVLET_PATH = "/api";

    /* Paths for Commands */

    // GET
    public static final String HOME = "/home";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String SIGNUP ="/signup";
    public static final String USERS = "/users";
    public static final String USERS_ = USERS + "/";
    public static final String PROFILE = "/profile";
    public static final String USER_PROFILE = USERS + PROFILE;
    public static final String GRADES = "/grades";
    public static final String USER_GRADES = USERS + GRADES;
    public static final String SPECIALITY = "/speciality";
    public static final String USER_SPECIALITY =  USERS + SPECIALITY;

    // POST
    public static final String USERS_UPDATE_SPEC = USERS + "/updateSpec";
    public static final String USERS_UPDATE_GRADES = USERS + "/updateGrades";

    /* Login errors */
    public static final String LOGIN_ERROR = LOGIN + "?error=true";
    public static final String LOGIN_LOGOUT = LOGIN + "?logout=true";

    /* JSP */
    private static final String PREFIX = "/WEB-INF/view";
    public static final String HOME_JSP = PREFIX + "/home.jsp";
    public static final String LOGIN_JSP = PREFIX + "/login.jsp";
    public static final String SIGNUP_JSP = PREFIX + "/signup.jsp";
    public static final String USERLIST_JSP = PREFIX + "/userList.jsp";
    public static final String USER_SPECIALITY_JSP = PREFIX + "/userSpeciality.jsp";
    public static final String USER_GRADES_JSP = PREFIX + "/userGrades.jsp";
    public static final String PAGE_403_JSP = PREFIX + "/errorPage/403.jsp";
    public static final String PAGE_404_JSP = PREFIX + "/errorPage/404.jsp";
}
