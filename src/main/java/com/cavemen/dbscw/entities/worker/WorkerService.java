package com.cavemen.dbscw.entities.worker;

import com.cavemen.dbscw.entities.worker.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public Worker getByLogin(String login) {
        return workerRepository.findByLogin(login);
    }

    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
    
    public Optional<Worker> getWorkerById(Long id){
        return workerRepository.findById(id);
    }
    
    public void deleteWorker(Long id){
        workerRepository.deleteById(id);
    }
}
