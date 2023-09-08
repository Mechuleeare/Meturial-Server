package com.meturial.domain.notification.domain;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.global.entity.BaseUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Table(name = "tbl_menu_notification")
public class MenuNotification extends BaseUUIDEntity {

    @Column(columnDefinition = "BIT(1)")
    private Boolean isActivated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "menu_id", nullable = false)
    private Menu menu;
}
