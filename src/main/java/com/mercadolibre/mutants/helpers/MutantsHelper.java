package com.mercadolibre.mutants.helpers;

import java.util.List;
import java.util.stream.Collectors;

public class MutantsHelper {

    public static String listToString(List<String> sequence) {
        return sequence.stream().map(Object::toString).collect(Collectors.joining(","));
    }

}
