package com.cavemen.dbscw.entities.worker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/getByLogin/{login}")
    public ResponseEntity<Optional<Worker>> getByLogin(@PathVariable("login") String login) {
        return new ResponseEntity(workerService.getByLogin(login), HttpStatus.OK);
    }
}
