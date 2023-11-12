package com.meturial.domain.menu.domain.repository;

import com.meturial.domain.menu.domain.Menu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.meturial.domain.menu.domain.QMenu.menu;
import static com.meturial.domain.user.domain.QUser.user;

@RequiredArgsConstructor
@Component
public class CustomMenuRepositoryImpl implements CustomMenuRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> findAllByDateAndUserId(LocalDate date, UUID userId) {
        return queryFactory
                .selectFrom(menu)
                .innerJoin(user)
                .on(menu.user.id.eq(user.id))
                .where(
                        menu.date.eq(date),
                        menu.user.id.eq(userId)
                )
                .fetch();
    }
}
