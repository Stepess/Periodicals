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
    SUBSCRIBE {
        {
            this.command = new SubscribeCommand();
        }
    },
    ADDPUBLICATION {
        {
            this.command = new AddPublicationCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }

    },
    EDITPUBLICATION {
        {
            this.command = new EditPublicationCommand();
        }
    },
    STATISTICS {
        {
            this.command = new StatisticsCommand();
        }
    },
    SHOWREPORT {
        {
            this.command = new ShowReportCommand();
        }
    },
    DELETEPUBLICATION {
        {
            this.command = new DeletePublicationCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    REPLENISH {
        {
            this.command = new ReplenishCommand();
        }
    },
    SEARCH {
        {
            this.command = new SearchCommand();
        }
    },
    DELETESUBSCRIPTION {
        {
            this.command = new DeleteSubscriptionCommand();
        }
    },
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }

}
