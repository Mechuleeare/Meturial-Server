package com.meturial.domain.notification.service;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.exception.MenuNotFoundException;
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
    private final SecurityFacade securityFacade;

    @Transactional(readOnly = true)
    public QueryNotificationSettingListResponse queryNotificationSettingListByDate(LocalDate date) {
        List<Menu> menuList = menuRepository.findAllByDateAndUserId(date, securityFacade.getCurrentUserId());
        List<NotificationSettingElement> notificationList = menuList
                .stream()
                .map(this::buildNotificationSettingElement)
                .toList();

        return new QueryNotificationSettingListResponse(notificationList);
    }

    private NotificationSettingElement buildNotificationSettingElement(Menu menu) {
        return new NotificationSettingElement(
                menu.getMenuType().toString(),
                menu.getIsActivated()
        );
    }

    @Transactional
    public void updateNotificationSettingByDate(UpdateNotificationSettingRequest request) {
        Menu menu = menuRepository.findById(request.getMenuId())
                .orElseThrow(() -> MenuNotFoundException.EXCEPTION);

        menu.checkMenuIsMine(securityFacade.getCurrentUserId());
        menu.updateMenuNotificationActivated(request.getIsActivated());
    }
}
