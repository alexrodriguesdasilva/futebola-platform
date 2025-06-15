package br.com.FutebolaPlatform.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.FutebolaPlatform.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> { 
}
