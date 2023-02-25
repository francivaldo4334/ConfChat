package br.com.confchat.api.repositorys;

import java.util.Optional;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.confchat.api.models.ChatMessage;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage,Integer> {
    Optional<ChatMessage> findById(int id);
    Collection<ChatMessage> findByUserId(int userId);
    void delete(ChatMessage notice);
    Collection<ChatMessage> findByIdAndContactId(int id,int contactId);
    Collection<ChatMessage> findByuserIdAndCode(int userId,String code);
}
