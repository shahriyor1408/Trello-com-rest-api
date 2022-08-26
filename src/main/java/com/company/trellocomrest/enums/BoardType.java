package com.company.trellocomrest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardType {
    PRIVATE("Private"),
    PUBLIC("Public");

    private final String key;
}
