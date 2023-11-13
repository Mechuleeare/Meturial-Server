package com.meturial.domain.menu.presentation;

import com.meturial.domain.menu.presentation.dto.request.CreateMenuRequest;
import com.meturial.domain.menu.presentation.dto.request.UpdateMenuRequest;
import com.meturial.domain.menu.presentation.dto.response.QueryMenuDetailResponse;
import com.meturial.domain.menu.presentation.dto.response.QueryMenuListResponse;
import com.meturial.domain.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{menu-id}")
    public void deleteMenu(@PathVariable("menu-id") UUID menuId) {
        menuService.deleteMenu(menuId);
    }

    @GetMapping
    public QueryMenuDetailResponse queryMenuDetailByDate(@RequestParam("date") LocalDate date) {
        return menuService.queryMenuDetailByDate(date);
    }

    @GetMapping("/list")
    public QueryMenuListResponse queryMenuListByYearAndMonth(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    ) {
        return menuService.queryMenuListByYearAndMonth(year, month);
    }
}
