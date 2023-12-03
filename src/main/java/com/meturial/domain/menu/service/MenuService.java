package com.meturial.domain.menu.service;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.exception.MenuExistException;
import com.meturial.domain.menu.facade.MenuFacade;
import com.meturial.domain.menu.presentation.dto.request.CreateMenuRequest;
import com.meturial.domain.menu.presentation.dto.request.UpdateMenuRequest;
import com.meturial.domain.menu.presentation.dto.response.MenuDetailElement;
import com.meturial.domain.menu.presentation.dto.response.MenuNotificationElement;
import com.meturial.domain.menu.presentation.dto.response.QueryMenuDetailResponse;
import com.meturial.domain.menu.presentation.dto.response.QueryMenuListResponse;
import com.meturial.domain.recipe.facade.ChoiceRecipeFacade;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ChoiceRecipeFacade choiceRecipeFacade;
    private final MenuFacade menuFacade;
    private final SecurityFacade securityFacade;

    public void createMenu(CreateMenuRequest request) {
        if (menuFacade.checkExistMenu(request.getDate(), request.getMenuType(), securityFacade.getCurrentUserId())) {
            throw MenuExistException.EXCEPTION;
        }
        menuRepository.save(Menu.builder()
                .date(request.getDate())
                .menuType(request.getMenuType())
                .isActivated(request.getIsActivated())
                .choiceRecipe(choiceRecipeFacade.findById(request.getChoiceRecipeId()))
                .user(securityFacade.getCurrentUser())
                .build());
    }

    @Transactional
    public void updateMenu(UUID menuId, UpdateMenuRequest request) {
        Menu menu = menuFacade.findById(menuId);
        menu.checkMenuIsMine(securityFacade.getCurrentUserId());
        menu.checkExistSameDateAndMenuType(request.getDate(), request.getMenuType());
        menu.updateMenu(
                request.getDate(),
                request.getMenuType(),
                choiceRecipeFacade.findById(request.getChoiceRecipeId()),
                request.getIsActivated()
        );
    }

    @Transactional
    public void deleteMenu(UUID menuId) {
        Menu menu = menuFacade.findById(menuId);
        menu.checkMenuIsMine(securityFacade.getCurrentUserId());
        menuRepository.delete(menu);
    }

    @Transactional(readOnly = true)
    public QueryMenuDetailResponse queryMenuDetailByDate(LocalDate date) {
        List<MenuDetailElement> menuDetailElements = menuRepository.findAllByDateAndUserId(date, securityFacade.getCurrentUserId())
                .stream()
                .map(MenuDetailElement::of)
                .toList();

        return new QueryMenuDetailResponse(date, menuDetailElements);
    }

    @Transactional(readOnly = true)
    public QueryMenuListResponse queryMenuListByYearAndMonth(Integer year, Integer month) {
        LocalDate targetDate = LocalDate.of(year, month, 1);
        List<Menu> menuList = menuRepository.findAllByBetweenCurrentMonthAndNextMonthAndUserId(targetDate, securityFacade.getCurrentUserId());
        Map<LocalDate, List<MenuNotificationElement>> menuNotificationMap = new TreeMap<>(
                menuList.stream().collect(Collectors.groupingBy(
                        Menu::getDate,
                        Collectors.mapping(MenuNotificationElement::of, Collectors.toList())
                ))
        );

        return new QueryMenuListResponse(menuNotificationMap);
    }
}
