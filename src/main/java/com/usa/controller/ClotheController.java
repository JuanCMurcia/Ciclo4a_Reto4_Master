package com.usa.controller;

import com.usa.model.Clothe;
import com.usa.service.ClotheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clothe")
@CrossOrigin("*")
public class ClotheController {

    @Autowired
    private ClotheService clotheService;

    @GetMapping("/all")
    public List<Clothe> getAll() {
        return clotheService.getAll();
    }

    @GetMapping("/{reference}")
    public Optional<Clothe> getClothe(@PathVariable("reference") String reference) {
        return clotheService.getClothe(reference);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Clothe create(@RequestBody Clothe gadget) {
        return clotheService.create(gadget);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Clothe update(@RequestBody Clothe gadget) {
        return clotheService.update(gadget);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("reference") String reference) {
        return clotheService.delete(reference);
    }
}
