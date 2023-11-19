package com.meturial.domain.notification.service;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.facade.MenuFacade;
import com.meturial.domain.notification.presentation.dto.request.UpdateNotificationSettingRequest;
import com.meturial.domain.notification.presentation.dto.response.NotificationSettingElement;
import com.meturial.domain.notification.presentation.dto.response.QueryNotificationSettingListResponse;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final MenuRepository menuRepository;
    private final MenuFacade menuFacade;
    private final SecurityFacade securityFacade;

    @Transactional(readOnly = true)
    public QueryNotificationSettingListResponse queryNotificationSettingListByDate(LocalDate date) {
        List<NotificationSettingElement> notificationList = menuRepository.findAllByDateAndUserId(date, securityFacade.getCurrentUserId())
                .stream()
                .map(NotificationSettingElement::of)
                .toList();

        return new QueryNotificationSettingListResponse(notificationList);
    }

    @Transactional
    public void updateNotificationSettingByDate(UpdateNotificationSettingRequest request) {
        Menu menu = menuFacade.findById(request.getMenuId());
        menu.checkMenuIsMine(securityFacade.getCurrentUserId());
        menu.updateMenuNotificationActivated(request.getIsActivated());
    }
}
