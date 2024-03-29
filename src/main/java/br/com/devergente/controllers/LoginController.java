package br.com.devergente.controllers;

import br.com.devergente.config.JwtUtil;
import br.com.devergente.models.usuarios.Usuario;
import br.com.devergente.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        if (userServices.findByEmail(usuario.getEmail(), usuario.getSenha())) {
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwtUtil.generateToken(usuario.getEmail()))
                    .build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
