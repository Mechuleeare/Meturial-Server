package com.meturial.domain.menu.facade;

import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.domain.type.MenuType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class MenuFacade {

    private final MenuRepository menuRepository;

    public boolean checkExistMenu(LocalDate date, MenuType menuType) {
        return menuRepository.existsByDateAndMenuType(date, menuType);
    }
}
