package com.company.trellocomrest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WorkspaceVisibility {
    PRIVATE("Private"),
    PUBLIC("Public");

    private final String key;
}
