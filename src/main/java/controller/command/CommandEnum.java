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
    },

    CATALOG {
        {
            this.command = new CatalogCommand();
        }
    },
    PAYMENTS {
        {
            this.command = new PaymentCommand();
        }
    },
    SUBSCRIPTIONS {
        {
            this.command = new SubscriptionCommand();
        }
    },
    PAY {
        {
            this.command = new PayCommand();
        }
    },
    SUBSCRIPT {
        {
            this.command = new SubscriptCommand();
        }
    },
    ADDPUBLICATION {
        {
            this.command = new AddPublicationCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }

}
