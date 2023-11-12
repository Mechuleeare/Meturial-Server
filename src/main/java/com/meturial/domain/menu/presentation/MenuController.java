package com.meturial.domain.menu.presentation;

import com.meturial.domain.menu.presentation.dto.request.UpdateMenuRequest;
import com.meturial.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/menu")
@RestController
public class MenuController {

    private final MenuService menuService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{menu-id}")
    public void updateMenu(@PathVariable("menu-id") UUID menuId,
                           @RequestBody UpdateMenuRequest request) {
        menuService.updateMenu(menuId, request);
    }
}
