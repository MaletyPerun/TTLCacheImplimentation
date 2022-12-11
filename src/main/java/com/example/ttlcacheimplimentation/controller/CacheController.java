package com.example.ttlcacheimplimentation.controller;

import com.example.ttlcacheimplimentation.dto.TTLObjectDto;
import com.example.ttlcacheimplimentation.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.ttlcacheimplimentation.util.ValidationUtil.checkNotBlank;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CacheController {
    private final CacheService cache;

    @GetMapping("GET")
    public ResponseEntity<TTLObjectDto> getCache(@RequestParam String key) {
        checkNotBlank(key);
        return ResponseEntity.ok(cache.get(key));
    }

    @GetMapping("KEYS")
    public ResponseEntity<List<String>> getListOfKeys(@RequestParam String key) {
        checkNotBlank(key);
        return ResponseEntity.ok(List.copyOf(cache.getKeys(key)));
    }

    @PostMapping(value = "SET", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setObject(@RequestParam String key, @Valid @RequestBody @NotNull TTLObjectDto ttlObjectDto) {
        checkNotBlank(key);
        cache.add(key, ttlObjectDto.getValue());
        return new ResponseEntity<>("Установлен ключ: " + key +
                " со значением: " + ttlObjectDto.getValue(), HttpStatus.CREATED);
    }

    @DeleteMapping("DEL")
    public ResponseEntity<Object> deleteObject(@RequestParam String key) {
        checkNotBlank(key);
        return new ResponseEntity<>("Удален ключ: " + key +
                " со значением: " + cache.delete(key).getValue(), HttpStatus.OK);
    }
}
