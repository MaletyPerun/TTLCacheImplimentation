package com.example.ttlcacheimplimentation.controller;

import com.example.ttlcacheimplimentation.model.CommandLine;
import com.example.ttlcacheimplimentation.repository.MyCache;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CacheController {

    // GET+, SET+, DEL+, KEYS+
    private final MyCache cache;

    // TODO: 06/12/2022 добавить обработку исключений и валидацию

    @GetMapping("GET")
    public String getCache(@RequestParam String key) {
        return cache.get(key);
    }

    @GetMapping("KEYS")
    public String getListOfKeys(@RequestParam String key) {
        return cache.getKeys(key).toString();
    }

    @PostMapping(value = "SET", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String setObject(@Valid @RequestBody @NotNull CommandLine commandLine) {
        cache.add(commandLine.getStrLine());
        return "set " + commandLine.getStrLine();
    }

    @DeleteMapping("DEL")
    public String deleteObject(@RequestParam String key) {
        String deletedKey = cache.delete(key);
        return deletedKey != null ? "Object with key: " + key + " is deleted" :
                "Object with key: " + key + " not found";
    }
}
