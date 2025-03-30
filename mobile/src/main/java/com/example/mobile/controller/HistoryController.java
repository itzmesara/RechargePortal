package com.example.mobile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.model.History;

import com.example.mobile.service.HistoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/History")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    // 201 Created – When the history record is created successfully.
    @PostMapping()
    public ResponseEntity<Object> addHistory(@RequestBody History history) {
        try {
            historyService.addHistory(history);
            return new ResponseEntity<>("History record is created", HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // 200 OK – When the history records are retrieved successfully.
    @GetMapping()
    public ResponseEntity<List<History>> getHistorys() {
        try {
            List<History> historys = historyService.getHistorys();
            return new ResponseEntity<>(historys, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<History>> Paging(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(historyService.getPages(page, size), HttpStatus.OK);
    }

    // 200 OK – When the history record is found.
    // 404 Not Found – When the history record is not found.
    @GetMapping("/{id}")
    public ResponseEntity<Optional<History>> getHistory(@PathVariable long id) {
        try {
            Optional<History> history = historyService.getHistory(id);
            return new ResponseEntity<>(history, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 200 OK – When the history record is updated successfully.
    // 404 Not Found – When the history record is not found.
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHistory(@PathVariable long id, @RequestBody History history) {
        try {
            historyService.updateHistory(id, history);
            return ResponseEntity.status(200).body("Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 204 No Content – When the history record is deleted successfully.
    // 404 Not Found – When the history record is not found.
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHistory(@PathVariable long id) {
        try {
            historyService.deleteHistory(id);
            return ResponseEntity.status(204).body("Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
