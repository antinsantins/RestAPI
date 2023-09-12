package org.antins.restapi.controller;

import org.antins.restapi.exceptions.StringEmptyException;
import org.antins.restapi.model.Symbol;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @PostMapping
    public ResponseEntity<List<Symbol>> calculatingFrequency(@RequestBody Map<String, String> request) {
        String str = request.get("str");
        if (str == null || str.isEmpty()) {
            throw new StringEmptyException("field is empty");
        }
        Map<Character, Integer> symbols = new HashMap<>();
        for (char a: str.toCharArray()) {
            symbols.put(a, symbols.getOrDefault(a, 0) +1);
        }

        List<Symbol> resaultList = symbols.entrySet().stream()
                .map(symbol -> new Symbol(symbol.getKey(), symbol.getValue()))
                .sorted(Comparator.comparingInt(Symbol::getAmount).reversed())
                .collect(Collectors.toList());

        return new ResponseEntity<>(resaultList, HttpStatus.OK);
    }
}
