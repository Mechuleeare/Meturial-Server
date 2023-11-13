package com.meturial.domain.menu.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class QueryMenuListResponse {
    private final Map<LocalDate, List<MenuNotificationElement>> menuMap;
}
