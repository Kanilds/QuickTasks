package com.topicosespeciais.quicktask.resources;

import com.topicosespeciais.quicktask.domain.QuickTask;
import com.topicosespeciais.quicktask.services.QuickTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/")
public class QuickTaskResource {

    @Autowired
    private QuickTaskService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuickTask> findById(@PathVariable Integer id) {

        QuickTask obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/open")
    public ResponseEntity<List<QuickTask>> listOpen() {
        List<QuickTask> list = service.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/close")
    public ResponseEntity<List<QuickTask>> listClose() {
        List<QuickTask> list = service.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping()
    public ResponseEntity<List<QuickTask>> findAll() {
        List<QuickTask> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<QuickTask> create(@RequestBody QuickTask obj){
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<QuickTask> update(@PathVariable Integer id, @RequestBody QuickTask obj){
        QuickTask newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }
}
