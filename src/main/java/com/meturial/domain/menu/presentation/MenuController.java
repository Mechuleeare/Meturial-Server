package com.meturial.domain.menu.presentation;

import com.meturial.domain.menu.presentation.dto.request.CreateMenuRequest;
import com.meturial.domain.menu.presentation.dto.request.UpdateMenuRequest;
import com.meturial.domain.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/menu")
@RestController
public class MenuController {

    private final MenuService menuService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createMenu(@RequestBody @Valid CreateMenuRequest request) {
        menuService.createMenu(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{menu-id}")
    public void updateMenu(@PathVariable("menu-id") UUID menuId,
                           @RequestBody @Valid UpdateMenuRequest request) {
        menuService.updateMenu(menuId, request);
    }
}
