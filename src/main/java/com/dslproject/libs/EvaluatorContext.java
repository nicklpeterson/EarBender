package com.dslproject.libs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EvaluatorContext {
    private int channel;
    private boolean rest;
    private boolean simulChild;
}
