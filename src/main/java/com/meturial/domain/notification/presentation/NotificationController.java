package com.meturial.domain.notification.presentation;

import com.meturial.domain.notification.presentation.dto.request.UpdateNotificationSettingRequest;
import com.meturial.domain.notification.presentation.dto.response.QueryNotificationSettingListResponse;
import com.meturial.domain.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/notification")
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public QueryNotificationSettingListResponse queryNotificationSettingListByDate(@RequestParam("date") LocalDate date) {
        return notificationService.queryNotificationSettingListByDate(date);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateNotificationSetting(@RequestBody @Valid UpdateNotificationSettingRequest request) {
        notificationService.updateNotificationSettingByDate(request);
    }
}
