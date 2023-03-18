package br.com.confchat.api.repositorys;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.confchat.api.models.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice,Integer>{
    Optional<Notice> findById(int id);
    Collection<Notice> findByUserId(int userId);
    void delete(Notice notice);
    Collection<Notice> findByUserIdAndAtivo(int userId,boolean ativo);
}
