package com.meturial.domain.user.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.meturial.domain.user.domain.QUser.user;

@RequiredArgsConstructor
@Component
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> findAllDeviceTokenInUserIdList(List<UUID> userIdList) {
        return queryFactory
                .select(user.deviceToken)
                .from(user)
                .where(user.id.in(userIdList))
                .fetch();
    }
}
