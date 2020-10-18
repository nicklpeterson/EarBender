package com.dslproject.libs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class ValidatorContext {
    private boolean simulParent;
    private boolean functionParent;

    public boolean hasSimulParent() {
        return this.simulParent;
    }

    public boolean hasFunctionParent() {
        return this.functionParent;
    }
}
