package com.example.mobile.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.mobile.model.History;
import com.example.mobile.repository.HistoryRepo;

@Service
public class HistoryService {
    @Autowired
    HistoryRepo historyRepo;

    public HistoryService(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    public void addHistory(History history) {
        historyRepo.insertHistory(history.getRechargePlan(), history.getDate(), history.getTime());
    }

    public Optional<History> getHistory(long id) {
        return historyRepo.findHistoryById(id);
    }

    public List<History> getHistorys() {
        return historyRepo.findHistories();
    }

    public History updateHistory(long id, History history) {
        Optional<History> historyExist = historyRepo.findById(id);
        if (historyExist.isPresent()) {
            History updateHistory = historyExist.get();
            if (history.getRechargePlan() != null) {
                updateHistory.setRechargePlan(history.getRechargePlan());
            }
            if (history.getDate() != null) {
                updateHistory.setDate(history.getDate());
            }
            if (history.getTime() != null) {
                updateHistory.setTime(history.getTime());
            }
            return historyRepo.save(history);
        } else {
            return null;
        }
    }

    public void deleteHistory(long id) {
        historyRepo.deleteById(id);
    }

    public Page<History> getPages(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return historyRepo.findAll(pageable);
    }

}
