package br.com.confchat.api.repositorys;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.confchat.api.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
    Optional<User> findById(int id);
    Optional<User> findByCode(String code);
    Optional<User> findByEmail(String email);
}
