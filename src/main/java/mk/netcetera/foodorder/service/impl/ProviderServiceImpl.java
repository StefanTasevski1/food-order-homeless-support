package mk.netcetera.foodorder.service.impl;

import lombok.RequiredArgsConstructor;
import mk.netcetera.foodorder.model.Provider;
import mk.netcetera.foodorder.model.exceptions.InvalidProviderIdException;
import mk.netcetera.foodorder.repository.ProviderRepository;
import mk.netcetera.foodorder.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

    @Override
    public Provider findById(Long id) {
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if (optionalProvider.isPresent()) {
            return optionalProvider.get();
        } else {
            throw new InvalidProviderIdException();
        }
    }

    @Override
    public List<Provider> listAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider create(String name) {
        Provider provider = new Provider(name);
        providerRepository.save(provider);
        return provider;
    }
}
