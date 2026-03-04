package mk.netcetera.foodorder.service;

import mk.netcetera.foodorder.model.Provider;

import java.util.List;

public interface ProviderService {

    Provider findById(Long id);

    List<Provider> listAll();

    Provider create(String name);
}
