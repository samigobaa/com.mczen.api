package com.mczen.api.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class LoginHistory {
    private Map<LocalDate, List<LoginEvent>> login;
    private Map<LocalDate, List<LoginEvent>> logout;


    public LoginHistory(Map<LocalDate, List<LoginEvent>> login, Map<LocalDate, List<LoginEvent>> logout) {
        this.login = login;
        this.logout = logout;
    }

    public Map<LocalDate, List<LoginEvent>> getLogin() {
        return login;
    }

    public Map<LocalDate, List<LoginEvent>> getLogout() {
        return logout;
    }
}
