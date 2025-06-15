package br.com.FutebolaPlatform.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.FutebolaPlatform.models.UserModel;
import br.com.FutebolaPlatform.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
