package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaInfoService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/pizzaInformationServlet")
public class PizzaInformationServlet {
    private final IPizzaInfoService service;

    public PizzaInformationServlet(IPizzaInfoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    protected ResponseEntity<PizzaInfoDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    protected ResponseEntity<List<PizzaInfoDTO>> getList() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    protected ResponseEntity<PizzaInfoDTO> doPost(@RequestBody PizzaInfoDTO data) {
        PizzaInfoDTO created = this.service.add(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<PizzaInfoDTO> doPut(@PathVariable long id,
                                            @PathVariable("update_date") long updateDateRaw,
                                            @RequestBody PizzaInfoDTO data) {
        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(updateDateRaw),
                ZoneId.of("UTC")
        );
        return ResponseEntity.ok(this.service.update(data, id, updateDate));
    }

    @DeleteMapping("/{id}/update_date/{update_date}")
    protected void doDelete(@PathVariable long id,
                            @PathVariable("update_date") long updateDateRaw) {
        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(updateDateRaw),
                ZoneId.of("UTC")
        );
        service.deleteById(id, updateDate);
    }
}