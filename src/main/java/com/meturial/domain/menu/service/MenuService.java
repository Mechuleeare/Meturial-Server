package com.meturial.domain.menu.service;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.exception.MenuExistException;
import com.meturial.domain.menu.exception.MenuNotFoundException;
import com.meturial.domain.menu.facade.MenuFacade;
import com.meturial.domain.menu.presentation.dto.request.CreateMenuRequest;
import com.meturial.domain.menu.presentation.dto.request.UpdateMenuRequest;
import com.meturial.domain.menu.presentation.dto.response.MenuDetailElement;
import com.meturial.domain.menu.presentation.dto.response.QueryMenuDetailResponse;
import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.domain.recipe.domain.repository.ChoiceRecipeRepository;
import com.meturial.domain.recipe.exception.ChoiceRecipeNotFoundException;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ChoiceRecipeRepository choiceRecipeRepository;
    private final MenuFacade menuFacade;
    private final SecurityFacade securityFacade;

    public void createMenu(CreateMenuRequest request) {
        if (menuFacade.checkExistMenu(request.getDate(), request.getMenuType())) {
            throw MenuExistException.EXCEPTION;
        }

        ChoiceRecipe choiceRecipe = choiceRecipeRepository.findById(request.getChoiceRecipeId())
                        .orElseThrow(() -> ChoiceRecipeNotFoundException.EXCEPTION);

        menuRepository.save(Menu.builder()
                .date(request.getDate())
                .menuType(request.getMenuType())
                .isActivated(request.getIsActivated())
                .choiceRecipe(choiceRecipe)
                .user(securityFacade.getCurrentUser())
                .build());
    }

    @Transactional
    public void updateMenu(UUID menuId, UpdateMenuRequest request) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> MenuNotFoundException.EXCEPTION);

        menu.checkMenuIsMine(securityFacade.getCurrentUserId());
        menu.checkExistSameDateAndMenuType(request.getDate(), request.getMenuType());

        ChoiceRecipe choiceRecipe = choiceRecipeRepository.findById(request.getChoiceRecipeId())
                .orElseThrow(() -> ChoiceRecipeNotFoundException.EXCEPTION);

        menu.updateMenu(
                request.getDate(),
                request.getMenuType(),
                choiceRecipe,
                request.getIsActivated()
        );
    }

    @Transactional
    public void deleteMenu(UUID menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> MenuNotFoundException.EXCEPTION);

        menu.checkMenuIsMine(securityFacade.getCurrentUserId());

        menuRepository.delete(menu);
    }

    @Transactional(readOnly = true)
    public QueryMenuDetailResponse queryMenuDetailByDate(LocalDate date) {
        List<Menu> menuList = menuRepository.findAllByDateAndUserId(date, securityFacade.getCurrentUserId());
        List<MenuDetailElement> menuDetailElements = menuList
                .stream()
                .map(this::buildMenuDetail)
                .toList();

        return new QueryMenuDetailResponse(
                date,
                menuDetailElements
        );
    }

    private MenuDetailElement buildMenuDetail(Menu menu) {
        return MenuDetailElement.builder()
                .menuId(menu.getId())
                .recipeId(menu.getMenuRecipeId())
                .recipeName(menu.getMenuRecipeName())
                .menuType(menu.getMenuType())
                .recipeImageUrl(menu.getMenuRecipeUrl())
                .build();
    }
}
