package com.meturial.domain.menu.domain.repository;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.type.MenuType;
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

    @Override
    public List<Menu> findAllByBetweenCurrentMonthAndNextMonthAndUserId(LocalDate targetDate, UUID userId) {
        return queryFactory
                .selectFrom(menu)
                .innerJoin(user)
                .on(menu.user.id.eq(user.id))
                .where(
                        menu.date.between(targetDate, targetDate.plusMonths(1).minusDays(1)),
                        menu.user.id.eq(userId)
                )
                .fetch();
    }

    @Override
    public List<UUID> findAllUserIdByMenuTypeAndDate(MenuType menuType, LocalDate date) {
        return queryFactory
                .select(menu.user.id)
                .from(menu)
                .innerJoin(user)
                .on(menu.user.id.eq(user.id))
                .where(
                        menu.menuType.eq(menuType),
                        menu.date.eq(date),
                        menu.isActivated.eq(true)
                )
                .fetch();
    }
}
