package com.example.ttlcacheimplimentation.controller;

import com.example.ttlcacheimplimentation.dto.TTLObjectDTO;
import com.example.ttlcacheimplimentation.model.CommandLine;
import com.example.ttlcacheimplimentation.repository.MyCache;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CacheController {
    private final MyCache cache;

    // TODO: 06/12/2022 добавить обработку исключений и валидацию

    @GetMapping("GET")
    public ResponseEntity<TTLObjectDTO> getCache(@RequestParam String key) {
        return ResponseEntity.ok(cache.get(key));
    }

    @GetMapping("KEYS")
    public List<String> getListOfKeys(@RequestParam String key) {
        return List.copyOf(cache.getKeys(key));
    }

    @PostMapping(value = "SET", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String setObject(@Valid @RequestBody @NotNull CommandLine commandLine) {
        cache.add(commandLine.getStrLine());
        return "set " + commandLine.getStrLine();
    }

    @DeleteMapping("DEL")
    public String deleteObject(@RequestParam String key) {
        cache.delete(key);
        return "Object with key: " + key + " is deleted";
    }
}
