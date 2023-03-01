package com.topicosespeciais.quicktask.services;

import com.topicosespeciais.quicktask.domain.QuickTask;
import com.topicosespeciais.quicktask.repository.QuickTaskRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    private final QuickTaskRepository quickTaskRepository;

    public DBService(QuickTaskRepository quickTaskRepository) {
        this.quickTaskRepository = quickTaskRepository;
    }

    public void instaciaBaseDados() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        QuickTask qt = new QuickTask(null, "Estudar", "aula Tópicos especias", sdf.parse("24/09/2022"), true);
        QuickTask qt2 = new QuickTask(null, "Estudar", "aula Tópicos especias", sdf.parse("24/09/2022"), true);
        QuickTask qt3 = new QuickTask(null, "Estudar", "aula Tópicos especias", sdf.parse("24/09/2022"), false);
        QuickTask qt4 = new QuickTask(null, "Estudar", "aula Tópicos especias", sdf.parse("24/09/2022"), false);

        quickTaskRepository.saveAll(Arrays.asList(qt, qt2, qt3, qt4));
    }
}
