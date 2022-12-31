package com.cavemen.dbscw.entities.worker;

import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public Worker getByLogin(String login) {
        return workerRepository.findByLogin(login);
    }
}
