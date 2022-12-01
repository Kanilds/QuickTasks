package com.topicosespeciais.quicktask.services;

import com.topicosespeciais.quicktask.domain.QuickTask;
import com.topicosespeciais.quicktask.repository.QuickTaskRepository;
import com.topicosespeciais.quicktask.services.exceptions.ObjNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuickTaskService {

    private final QuickTaskRepository repository;

    public QuickTaskService(QuickTaskRepository repository) {
        this.repository = repository;
    }

    public QuickTask findById(Integer id) {
        Optional<QuickTask> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não encontrado: " + id + ", Tipo: " + QuickTask.class.getName()));
    }

    public List<QuickTask> findAllOpen() {
        return repository.findAllOpen();
    }

    public List<QuickTask> findAllClose() {
        return repository.findAllClose();
    }

    public List<QuickTask> findAll() {
        return repository.findAll();
    }

    public QuickTask create(QuickTask obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public QuickTask update(Integer id, QuickTask obj) {
        QuickTask newObj = findById(id);
        newObj.setTitulo(obj.getTitulo());
        newObj.setDataParaFinalizar(obj.getDataParaFinalizar());
        newObj.setDescricao(obj.getDescricao());
        newObj.setFinalizado(obj.getFinalizado());
        return repository.save(newObj);
    }
}
