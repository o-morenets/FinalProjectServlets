package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.GlobalConstants;
import ua.training.admission.view.Paths;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandHolder
 */
public class CommandHolder {

    private Map<String, Command> commands;

    public CommandHolder() {
        initCommands();
    }

    private void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put(GlobalConstants.GET + Paths.HOME, new PageHomeCommand());
                put(GlobalConstants.GET + Paths.LOGIN, new PageLoginCommand());
                put(GlobalConstants.GET + Paths.SIGNUP, new PageSignupCommand());

                put(GlobalConstants.POST + Paths.LOGIN, new LoginCommand());
                put(GlobalConstants.POST + Paths.LOGOUT, new LogoutCommand());
                put(GlobalConstants.POST + Paths.SIGNUP, new SignupCommand());
            }
        };
    }

    public Command getCommand(String key) {
        return commands.getOrDefault(key, (request, response) -> {
            request.setAttribute(Attributes.PAGE_TITLE, "title.home");
            response.sendRedirect(request.getContextPath() + Paths.PAGE_404);
            return Paths.REDIRECTED;
        });
    }
}
