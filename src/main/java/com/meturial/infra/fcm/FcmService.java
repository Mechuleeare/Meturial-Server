package com.meturial.infra.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.domain.type.MenuType;
import com.meturial.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FcmService {

    private static final String BREAKFAST_NOTIFICATION_TITLE = "아침식사 알림";
    private static final String LUNCH_NOTIFICATION_TITLE = "점심식사 알림";
    private static final String DINNER_NOTIFICATION_TITLE = "저녁식사 알림";
    private static final String NOTIFICATION_BODY = "등록하신 식단으로 맛있는 식사하세요";
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public void sendMenuNotificationByMenuType(MenuType menuType) {
        List<UUID> userIdList = menuRepository.findAllUserIdByMenuTypeAndDate(menuType, LocalDate.now());
        List<String> deviceTokenList = userRepository.findAllDeviceTokenInUserIdList(userIdList);

        MulticastMessage multicastMessage = MulticastMessage.builder()
                .addAllTokens(deviceTokenList)
                .setNotification(
                        Notification.builder()
                                .setTitle(getNotificationTitleByMenuType(menuType))
                                .setBody(NOTIFICATION_BODY)
                                .build()
                )
                .build();

        FirebaseMessaging.getInstance().sendMulticastAsync(multicastMessage);
    }

    private String getNotificationTitleByMenuType(MenuType menuType) {
        return switch (menuType) {
            case BREAKFAST -> BREAKFAST_NOTIFICATION_TITLE;
            case LUNCH -> LUNCH_NOTIFICATION_TITLE;
            case DINNER -> DINNER_NOTIFICATION_TITLE;
        };
    }
}
