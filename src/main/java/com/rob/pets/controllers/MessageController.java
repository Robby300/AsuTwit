package com.rob.pets.controllers;

import com.rob.pets.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 5;
    private final List<Map<String, String>> messages = new ArrayList<>() {{
        add(new HashMap<>() {{
            put("id", "1");
            put("text", "Иван Горшенин");
        }});
        add(new HashMap<>() {{
            put("id", "2");
            put("text", "Игорь Овчаренко");
        }});
        add(new HashMap<>() {{
            put("id", "3");
            put("text", "Александр Сычков");
        }});
        add(new HashMap<>() {{
            put("id", "4");
            put("text", "Алексей Томинович");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessageById(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(String id) {
        return messages
                .stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id,
                                      @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(message.get("id"));
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);
        return messageFromDb;
    }
}
