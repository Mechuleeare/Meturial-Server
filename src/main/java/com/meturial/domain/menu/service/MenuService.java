package com.meturial.domain.menu.service;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.repository.MenuRepository;
import com.meturial.domain.menu.exception.MenuExistException;
import com.meturial.domain.menu.exception.MenuNotFoundException;
import com.meturial.domain.menu.facade.MenuFacade;
import com.meturial.domain.menu.presentation.dto.request.CreateMenuRequest;
import com.meturial.domain.menu.presentation.dto.request.UpdateMenuRequest;
import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.domain.recipe.domain.repository.ChoiceRecipeRepository;
import com.meturial.domain.recipe.exception.ChoiceRecipeNotFoundException;
import com.meturial.domain.user.domain.User;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                choiceRecipe
        );
    }
}
