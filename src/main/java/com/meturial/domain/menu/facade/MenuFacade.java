package com.meturial.domain.menu.facade;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.domain.type.MenuType;
import com.meturial.domain.menu.exception.MenuNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MenuFacade {

    private final MenuRepository menuRepository;

    public boolean checkExistMenu(LocalDate date, MenuType menuType) {
        return menuRepository.existsByDateAndMenuType(date, menuType);
    }

    public Menu findById(UUID menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> MenuNotFoundException.EXCEPTION);
    }
}
