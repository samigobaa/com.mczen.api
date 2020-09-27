package com.mczen.api.service;

import com.mczen.api.model.LoginEvent;
import com.mczen.api.model.LoginEventType;
import com.mczen.api.model.LoginHistory;
import com.mczen.api.repository.LoginEventRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class LoginEventServiceTest {

    private LoginEventService underTest;
    private LoginEventRepository loginEventRepository = Mockito.mock(LoginEventRepository.class);

    @Before
    public void init() {
        underTest = new LoginEventService(loginEventRepository);
    }


    @Test
    public void shouldGetAllLoginEvent() {
        // Given
        List<LoginEvent> events = new ArrayList<>();
        final LocalDateTime now = LocalDateTime.now();
        events.add(new LoginEvent("sami", LoginEventType.LOGIN, now));
        events.add(new LoginEvent("sami", LoginEventType.LOGIN, now.plusHours(1)));
        final LocalDateTime yesterday = now.minusDays(1);
        events.add(new LoginEvent("sami", LoginEventType.LOGIN, yesterday));
        events.add(new LoginEvent("sami", LoginEventType.LOGOUT, yesterday));
        when(loginEventRepository.getAll()).thenReturn(events);

        // when
        LoginHistory loginHistory = underTest.getAll();

        // Then
        Assert.assertNotNull(loginHistory);
        Assert.assertEquals(loginHistory.getLogin().size(), 2);
        Assert.assertEquals(loginHistory.getLogout().size(), 1);
        Assert.assertEquals(loginHistory.getLogin().get(now.toLocalDate()).size(), 2);
        Assert.assertEquals(loginHistory.getLogin().get(yesterday.toLocalDate()).size(), 1);
        Assert.assertEquals(loginHistory.getLogout().get(yesterday.toLocalDate()).size(), 1);

        // assert that events are well grouped by action
        Assert.assertTrue(loginHistory.getLogin().get(now.toLocalDate()).get(0).getAction() == LoginEventType.LOGIN);
        Assert.assertTrue(loginHistory.getLogout().get(yesterday.toLocalDate()).get(0).getAction() == LoginEventType.LOGOUT);
        // assert that events are well grouped by date
        Assert.assertTrue(loginHistory.getLogin().get(now.toLocalDate()).get(0).getDateTime().equals(now));
        Assert.assertTrue(loginHistory.getLogout().get(yesterday.toLocalDate()).get(0).getDateTime().equals(yesterday));


    }

}
