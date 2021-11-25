package pl.goreit.burger.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.goreit.burger.domain.api.exceptions.DuplicateException;
import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
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

    public BurgerView get(String name) throws EntityNotFoundException {
        Burger burger = burgerRepo.findBurgerByNameAndStatus(name, Burger.Status.ACTIVE);
        if (burger == null) {
            throw new EntityNotFoundException("burger not exists with name = " + name);
        }
        return burgerFromModelToViewConverter.convert(burger);
    }


    public List<BurgerView> getAll() {

        return burgerRepo.findAllByStatus(Burger.Status.ACTIVE).stream()
                .map(burgerFromModelToViewConverter::convert)
                .collect(Collectors.toList());
    }

    public BurgerView create(BurgerView burgerView) throws DuplicateException {
        Burger burger = new Burger(UUID.randomUUID().toString(), burgerView.getName(), burgerView.getPrice());
        Burger exist = burgerRepo.findBurgerByNameAndStatus(burgerView.getName(), Burger.Status.ACTIVE);
        if (exist != null) {
            throw new DuplicateException("Burgery already exist with name " + burgerView.getName());
        }
        Burger save = burgerRepo.save(burger);
        return burgerView;
    }

    public BurgerView update(BurgerView burgerView) throws EntityNotFoundException {
        String name = burgerView.getName();
        Burger burgerByName = burgerRepo.findBurgerByNameAndStatus(name, Burger.Status.ACTIVE);
        if (burgerByName == null) {
            throw new EntityNotFoundException("Burger does not exist with name " + burgerView.getName());
        }
        burgerByName.setName(burgerView.getName());
        burgerByName.setPrice(burgerView.getPrice());
        Burger save = burgerRepo.save(burgerByName);
        return burgerView;
    }

    public boolean delete(String name) throws DuplicateException {
        Burger burger = burgerRepo.findBurgerByNameAndStatus(name, Burger.Status.ACTIVE);
        if (burger == null) {
            throw new DuplicateException("Burger does not exist with name " + name);
        }
        if (burger.getStatus() == Burger.Status.DELETED) {
            LOGGER.info("trying to delete already deleted burger" + burger);
            throw new IllegalArgumentException("cannot delete already deleted burger " + burger);
        }
        boolean b = burger.markAsDeleted();
        burgerRepo.save(burger);
        LOGGER.info("deleted burger: " + burger.toString());
        return true;
    }
}
