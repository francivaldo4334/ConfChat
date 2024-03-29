package br.com.confchat.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.confchat.api.models.AuthorizeRequest;
import br.com.confchat.api.models.User;
import br.com.confchat.api.viewmodels.LogInVM;
import br.com.confchat.api.viewmodels.LogUpVM;
import br.com.confchat.api.viewmodels.NewPasswordVM;
import br.com.confchat.api.viewmodels.TokenVM;
import br.com.confchat.api.repositorys.RecoverPasswordRepository;
import br.com.confchat.api.repositorys.UserRepository;
import br.com.confchat.api.utils.CodeGenerete;
import br.com.confchat.api.utils.EmailHelper;
import br.com.confchat.api.utils.JwtUtils;
import br.com.confchat.api.utils.RegexStrings;
import br.com.confchat.api.utils.ValidHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/auth")
// @Api(value = "teste")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecoverPasswordRepository recoverPasswordRepository;
    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("/register")
    public ResponseEntity<String> Logup(@RequestBody LogUpVM infoLogUp){
        User registeredUser = new User(
            infoLogUp.getName(),
            infoLogUp.getEmail(),
            infoLogUp.getPassword()
        );
        if(!ValidHelper.isValidName(registeredUser.getName()))
            return ResponseEntity.badRequest().body("name-invalid");

        if(!ValidHelper.isValidEmail(registeredUser.getEmail()))
            return ResponseEntity.badRequest().body("email-invalid");
        
        Optional<User> existingUser = userRepository.findByEmail(registeredUser.getEmail());
        if(!existingUser.isEmpty())
            return ResponseEntity.badRequest().body("email-unavailable");

        String code = CodeGenerete.generate();
        while(!userRepository.findByCode(code).isEmpty())
            code = CodeGenerete.generate();

        registeredUser.setCode(code);
        registeredUser.setPassword(encoder.encode(registeredUser.getPassword()));

        String savedUser = userRepository.save(registeredUser).getCode();

        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenVM> LogIn(@RequestBody LogInVM login){
        Optional<User> optUser = userRepository.findByEmail(login.getEmail());
        if(optUser.isEmpty()||!encoder.matches(login.getPassword(), optUser.get().getPassword()))
            return ResponseEntity.ok(new TokenVM());
        String _token = JwtUtils.generateJwt(optUser.get());
        TokenVM response = new TokenVM();
        response.setId(optUser.get().getId());
        response.setType("Bearer");
        response.setToken(_token);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/request-recover-password")
    public ResponseEntity<String> RequestRecoverPassword(@PathVariable String email){
        var user = userRepository.findByEmail(email);
        if(user.isEmpty())
            return ResponseEntity.badRequest().body("user not found.");
        AuthorizeRequest pr = new AuthorizeRequest();
        pr.setUserId(user.get().getId());
        pr.setCreatedIn(new Date(System.currentTimeMillis()));
        recoverPasswordRepository.save(pr);
        EmailHelper.recoverPassword(email, String.valueOf(pr.getId()));
        return ResponseEntity.ok("email sent.");
    }
    @PutMapping("/recover-password")
    public ResponseEntity<String> RecoverPassword(@RequestBody NewPasswordVM newPasswordVM){
        var request = recoverPasswordRepository.findById(newPasswordVM.getCode());
        if(request.isEmpty())
            return ResponseEntity.badRequest().body("request not found.");
        if(new Date(System.currentTimeMillis()).getTime()>request.get().getCreatedIn().getTime()){
            recoverPasswordRepository.delete(request.get());
            return ResponseEntity.badRequest().body("request not found.");
        }
        var user = userRepository.findById(request.get().getUserId());
        if(user.isEmpty())
            return ResponseEntity.badRequest().body("user not found.");
        user.get().setPassword(newPasswordVM.getNewPassword());
        userRepository.save(user.get());
        return ResponseEntity.ok(null);
    }
}
