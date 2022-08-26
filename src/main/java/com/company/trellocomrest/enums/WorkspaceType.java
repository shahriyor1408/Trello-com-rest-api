package com.company.trellocomrest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WorkspaceType {
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    HUMAN_RESOURCES("Human resources"),
    EDUCATION("Education"),
    SALES_CRM("Sales CRM"),
    OTHER("Other");

    private final String key;
}
