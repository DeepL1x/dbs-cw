package com.cavemen.dbscw.entities.provider;

import com.cavemen.dbscw.entities.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider addProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public Provider updateProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Optional<Provider> getProviderById(Long id){
        return providerRepository.findById(id);
    }

    public void deleteProvider(Long id){
        providerRepository.deleteById(id);
    }

    public void connect(Long providerID, Long categoryID) {
        providerRepository.connect(providerID, categoryID);
    }

    public Set<String> getCategories(Long id) {
        return providerRepository.getCategories(id);
    }
}
