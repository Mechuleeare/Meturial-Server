package com.meturial.global.scheduler;

import com.meturial.domain.menu.domain.type.MenuType;
import com.meturial.infra.fcm.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendMenuNotificationScheduler {

    private final FcmService fcmService;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendBreakfastMenuNotification() {
        fcmService.sendMenuNotificationByMenuType(MenuType.BREAKFAST);
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void sendLunchMenuNotification() {
        fcmService.sendMenuNotificationByMenuType(MenuType.LUNCH);
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void sendDinnerMenuNotification() {
        fcmService.sendMenuNotificationByMenuType(MenuType.DINNER);
    }
}
