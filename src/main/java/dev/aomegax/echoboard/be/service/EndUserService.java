package dev.aomegax.echoboard.be.service;

import dev.aomegax.echoboard.be.dto.request.EndUserReq;
import dev.aomegax.echoboard.be.model.EndUser;
import dev.aomegax.echoboard.be.repository.EndUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndUserService {

    private final EndUserRepository endUserRepository;

    public EndUserService(EndUserRepository userRepository) {
        this.endUserRepository = userRepository;
    }

    public EndUser saveUser(EndUserReq user) {
        ModelMapper modelMapper = new ModelMapper();
        return endUserRepository.save(modelMapper.map(user, EndUser.class));
    }

    public List<EndUser> getAllEndUsers() {
        return endUserRepository.findAll();
    }

    public void deleteUser(String id) {
        endUserRepository.deleteById(id);
    }
}
