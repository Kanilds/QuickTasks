package com.topicosespeciais.quicktask.repository;

import com.topicosespeciais.quicktask.domain.QuickTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuickTaskRepository extends JpaRepository<QuickTask, Integer> {

    @Query("SELECT obj FROM QuickTask obj WHERE obj.finalizado = false ORDER BY obj.dataParaFinalizar")
    List<QuickTask> findAllOpen();

    @Query("SELECT obj FROM QuickTask obj WHERE obj.finalizado = true ORDER BY obj.dataParaFinalizar")
    List<QuickTask> findAllClose();
}
