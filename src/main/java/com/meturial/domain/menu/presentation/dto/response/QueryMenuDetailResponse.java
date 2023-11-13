package com.meturial.domain.menu.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMenuDetailResponse {
    private final LocalDate date;
    private final List<MenuDetailElement> menuDetailList;
}
