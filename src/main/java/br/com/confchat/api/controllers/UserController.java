package br.com.confchat.api.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.confchat.api.models.User;
import br.com.confchat.api.repositorys.ContactRepository;
import br.com.confchat.api.repositorys.NoticeRepository;
import br.com.confchat.api.repositorys.UserRepository;
import br.com.confchat.api.utils.JwtUtils;
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
}
