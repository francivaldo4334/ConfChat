package br.com.confchat.api.repositorys;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.confchat.api.models.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Integer>{
    Optional<Contact> findById(int id);
    Optional<Contact> findByCode(String code);
    Optional<Contact> findByIdAndCode(int id,String code);
    Collection<Contact> findByUserId(int userId);
    void delete(Contact notice);
}
