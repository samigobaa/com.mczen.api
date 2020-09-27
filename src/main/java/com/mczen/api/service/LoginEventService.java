package com.mczen.api.service;


import com.mczen.api.model.LoginEvent;
import com.mczen.api.model.LoginEventType;
import com.mczen.api.model.LoginHistory;
import com.mczen.api.repository.LoginEventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginEventService {

    private final LoginEventRepository loginEventRepository;

    public LoginEventService() {
        loginEventRepository = new LoginEventRepository();
    }

    public LoginEventService(LoginEventRepository loginEventRepository) {
        this.loginEventRepository = loginEventRepository;
    }

    public LoginHistory getAll() {
        final List<LoginEvent> events = this.loginEventRepository.getAll();
        final Map<LoginEventType, Map<LocalDate, List<LoginEvent>>> groupedEvents = events.stream()
                .collect(Collectors.groupingBy(LoginEvent::getAction,
                        Collectors.groupingBy(loginEvent -> loginEvent.getDateTime().toLocalDate())));
        return new LoginHistory(groupedEvents.get(LoginEventType.LOGIN), groupedEvents.get(LoginEventType.LOGOUT));
    }
}
