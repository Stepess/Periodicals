package controller.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }

}
