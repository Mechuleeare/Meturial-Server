package com.meturial.domain.notification.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNotificationSettingListResponse {
    private final List<NotificationSettingElement> todayNotificationList;
}
