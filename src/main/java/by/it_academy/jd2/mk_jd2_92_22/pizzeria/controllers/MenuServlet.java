package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuServlet {

    private final IMenuService service;

    public MenuServlet(IMenuService service) {
        this.service = service;
    }

   @GetMapping("/{id}")
    protected ResponseEntity<MenuDTO> get(@PathVariable long id) {
       return ResponseEntity.ok(service.read(id));
    }

    @GetMapping
    protected ResponseEntity<List<MenuDTO>> getList() {
        return ResponseEntity.ok(service.get());
    }

    @PostMapping
    protected ResponseEntity<MenuDTO> doPost(@RequestBody MenuDTO data) {
        MenuDTO created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<MenuDTO> doPut(@PathVariable long id,
                                         @PathVariable("update_date") long updateDateRaw,
                                         @RequestBody MenuDTO data) {
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
        service.delete(id, updateDate);
    }
}
