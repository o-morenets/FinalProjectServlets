package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Paths;
import ua.training.admission.view.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandHolder - storage for commands
 *
 * @author Oleksii Morenets
 */
public class CommandHolder {

    /* Command holder map */
    private Map<String, Command> commands;

    /**
     * Constructs Command Holder
     */
    public CommandHolder() {
        initCommands();
    }

    /**
     * Initialize commands
     */
    private void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put(Constants.GET + Paths.HOME, new PageHomeCommand());
                put(Constants.GET + Paths.LOGIN, new PageLoginCommand());
                put(Constants.GET + Paths.SIGNUP, new PageSignupCommand());
                put(Constants.GET + Paths.USERS, new UserListCommand());
                put(Constants.GET + Paths.USER_SPECIALITY, new PageUserSpecialityCommand());
                put(Constants.GET + Paths.USER_PROFILE, new UserProfileCommand());
                put(Constants.GET + Paths.USER_GRADES, new PageUserGradesCommand());

                put(Constants.POST + Paths.LOGIN, new LoginCommand());
                put(Constants.POST + Paths.LOGOUT, new LogoutCommand());
                put(Constants.POST + Paths.SIGNUP, new SignupCommand());
                put(Constants.POST + Paths.USERS_UPDATE_SPEC, new UpdateSpecialityCommand());
                put(Constants.POST + Paths.USERS_UPDATE_GRADES, new UpdateGradesCommand());
            }
        };
    }

    /**
     * Get command from commands holder
     *
     * @param key command key
     * @return command object or default generated command
     */
    public Command getCommand(String key) {
        return commands.getOrDefault(key, (request, response) -> {
            request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_404);

            return Paths.PAGE_404_JSP;
        });
    }
}
