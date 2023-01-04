package com.cavemen.dbscw.entities.worker;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/all")
    public ResponseEntity<List<Worker>> getAllWorkers(){
        return new ResponseEntity<>(workerService.getAllWorkers(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Worker>> getWorker(@PathVariable("id") Long id){
        return new ResponseEntity<>(workerService.getWorkerById(id), HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker) {
        return new ResponseEntity<>(workerService.addWorker(worker), HttpStatus.OK);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Worker> updateWorker(@RequestBody Worker worker){
        return new ResponseEntity<>(workerService.updateWorker(worker), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable("id") Long id){
        workerService.deleteWorker(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
