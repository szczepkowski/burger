package pl.goreit.burger.domain.service;

import org.springframework.stereotype.Service;
import pl.goreit.burger.domain.api.view.ProfileView;
import pl.goreit.burger.domain.converter.ProfileFromModelToViewConverter;
import pl.goreit.burger.domain.model.Profile;
import pl.goreit.burger.infrastructure.ProfileRepo;

@Service
public class ProfileService {

    private final ProfileFromModelToViewConverter profileFromModelToViewConverter;
    private final ProfileRepo profileRepo;

    public ProfileService(ProfileRepo profileRepo, ProfileFromModelToViewConverter profileFromModelToViewConverter) {
        this.profileRepo = profileRepo;
        this.profileFromModelToViewConverter = profileFromModelToViewConverter;
    }


    public ProfileView get(String login) {
        Profile byLogin = profileRepo.findByLogin(login);
        return profileFromModelToViewConverter.convert(byLogin);
    }
}
