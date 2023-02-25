package br.com.confchat.api.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.confchat.api.models.ChatMessage;
import br.com.confchat.api.models.User;
import br.com.confchat.api.repositorys.ChatMessageRepository;
import br.com.confchat.api.repositorys.ContactRepository;
import br.com.confchat.api.repositorys.NoticeRepository;
import br.com.confchat.api.repositorys.UserRepository;
import br.com.confchat.api.utils.JwtUtils;
import br.com.confchat.api.viewmodels.SendMessageVM;
import br.com.confchat.api.viewmodels.UserVM;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping
    public ResponseEntity getUserInfo(@RequestHeader("Authorization") String bearerToken){
        var jwt = JwtUtils.verify(bearerToken);
        if(jwt == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        int id = Integer.parseInt(jwt.getIssuer());
        Optional<User> optUser = userRepository.findById(id);
        UserVM response = new UserVM(
            optUser.get().getPhotoUrl(),
            optUser.get().getCode(),
            optUser.get().getPermitions(),
            optUser.get().getEmail()
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/notifications")
    public ResponseEntity getNotifications(@RequestHeader("Authorization") String bearerToken){
        var jwt = JwtUtils.verify(bearerToken);
        if(jwt == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
         var listRespose = noticeRepository.findByUserId(Integer.parseInt(jwt.getIssuer()));
         return ResponseEntity.ok().body(listRespose);
    }
    @GetMapping("/contacts")
    public ResponseEntity getContacts(@RequestHeader("Authorization") String bearerToken){
        var jwt = JwtUtils.verify(bearerToken);
        if(jwt == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        var listResponse = contactRepository.findByUserId(Integer.parseInt(jwt.getIssuer()));
        return ResponseEntity.ok().body(listResponse);
    }
    @PostMapping("/send_message")
    public ResponseEntity sendMessage(@RequestHeader("Authorization") String bearerToken,@RequestBody SendMessageVM message){
        var jwt = JwtUtils.verify(bearerToken);
        if(jwt == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        var user1 = userRepository.findById(Integer.parseInt(jwt.getIssuer()));
        if(user1.isEmpty())
            return ResponseEntity.badRequest().body("user1 not found");
        var user2 = userRepository.findByCode(message.getCode());
        if(user2.isEmpty())
            return ResponseEntity.badRequest().body("user2 not found");
        var chatMessge = new ChatMessage();
        chatMessge.setUserId(user1.get().getId());
        chatMessge.setContactId(user2.get().getId());
        chatMessge.setVisualized(true);
        chatMessge.setText(message.getText());
        chatMessge.setPerson(1);
        chatMessageRepository.save(chatMessge);
        var chatMessgeContact = new ChatMessage();
        chatMessgeContact.setUserId(user2.get().getId());
        chatMessgeContact.setContactId(user1.get().getId());
        chatMessgeContact.setVisualized(false);
        chatMessgeContact.setText(message.getText());
        chatMessge.setPerson(2);
        chatMessageRepository.save(chatMessgeContact);
        return ResponseEntity.ok().body(message.getCode());
    }
}
