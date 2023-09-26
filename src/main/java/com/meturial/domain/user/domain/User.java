package com.meturial.domain.user.domain;

import com.meturial.global.entity.BaseUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_user")
public class User extends BaseUUIDEntity {

    @Column(columnDefinition = "VARCHAR(64)", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(255) default '기본이미지'")
    private String profileImageUrl;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String deviceToken;

    @Column(columnDefinition = "VARCHAR(300)")
    private String allergyInfo;

    public void modifyMypage(String profileImageUrl, String name, String allergyInfo) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.allergyInfo = allergyInfo;
    }
}
