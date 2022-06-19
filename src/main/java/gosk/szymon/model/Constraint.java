package gosk.szymon.model;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public enum Constraint {

    LESS_OR_EQUAL("\u2264"),
    EQUAL("="),
    GREATER_OR_EQUAL("\u2265");

    @Getter
    private final String representation;

    Constraint(@NotNull String representation) {
        this.representation = representation;
    }

}
