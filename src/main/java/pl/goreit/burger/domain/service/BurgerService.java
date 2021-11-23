package pl.goreit.burger.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.goreit.burger.domain.api.view.BurgerView;
import pl.goreit.burger.domain.model.Burger;
import pl.goreit.burger.domain.converter.BurgerFromModelToViewConverter;
import pl.goreit.burger.infrastructure.BurgerRepo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BurgerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BurgerService.class);

    private final BurgerRepo burgerRepo;

    private final BurgerFromModelToViewConverter burgerFromModelToViewConverter;

    public BurgerService(BurgerRepo burgerRepo, BurgerFromModelToViewConverter burgerFromModelToViewConverter) {
        this.burgerRepo = burgerRepo;
        this.burgerFromModelToViewConverter = burgerFromModelToViewConverter;
    }

    public List<BurgerView> getAll() {

        return burgerRepo.findAllByStatus(Burger.Status.ACTIVE).stream()
                .map(burgerFromModelToViewConverter::convert)
                .collect(Collectors.toList());
    }

    public BurgerView create(BurgerView burgerView) {
        Burger burger = new Burger(UUID.randomUUID().toString(), burgerView.getName(), burgerView.getPrice());
        Burger save = burgerRepo.save(burger);
        return burgerView;
    }

    public BurgerView update(BurgerView burgerView) {
        String name = burgerView.getName();
        Burger burgerByName = burgerRepo.findBurgerByName(name);
        burgerByName.setName(burgerView.getName());
        burgerByName.setPrice(burgerView.getPrice());
        Burger save = burgerRepo.save(burgerByName);
        return burgerView;
    }

    public boolean delete(String name) {
        Burger burger = burgerRepo.findBurgerByName(name);
        if (burger.getStatus() == Burger.Status.DELETED) {
            LOGGER.info("trying to delete already deleted burger" + burger);
            throw new IllegalArgumentException("cannot delete already deleted burger" + burger.toString());
        }
        boolean b = burger.markAsDeleted();
        burgerRepo.save(burger);
        LOGGER.info("deleted burger: " + burger.toString());
        return true;
    }
}
