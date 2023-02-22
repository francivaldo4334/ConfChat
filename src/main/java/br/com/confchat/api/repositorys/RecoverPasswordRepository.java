package br.com.confchat.api.repositorys;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.confchat.api.models.AuthorizeRequest;

@Repository
public interface RecoverPasswordRepository extends CrudRepository<AuthorizeRequest,Integer>{
    Optional<AuthorizeRequest> findById(int id);
    Collection<AuthorizeRequest> findAll();
    void delete(AuthorizeRequest passwordRequest);
}