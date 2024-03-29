package com.example.DSWII_CL3_NovoaLuis.controller;

import com.example.DSWII_CL3_NovoaLuis.model.bd.Usuario;
import com.example.DSWII_CL3_NovoaLuis.model.response.UsuarioResponse;
import com.example.DSWII_CL3_NovoaLuis.service.DetalleUsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"https://www.cibertec.edu.pe"})
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {
    private DetalleUsuarioService detalleUsuarioService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> autenticarUsuario(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password
    )throws Exception{
        try{
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(usuario,password)
                    );
            if(authentication.isAuthenticated()){
                Usuario objUsuario = detalleUsuarioService.findByNomusuario(usuario);
                String token = generarToken(objUsuario);
                UsuarioResponse usuarioResponse = new UsuarioResponse(
                        objUsuario.getIdusuario(), objUsuario.getNomusuario(),token
                );
                return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
            }else
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception ex){
            throw new Exception("usuario y/o password incorrecto");
        }
    }

    private String generarToken(Usuario usuario){
        String clave = "@CL3_NovoaLuis";
        List<GrantedAuthority> authorityList = detalleUsuarioService
                .obtenerListaRolesUsuario(usuario.getRoles());
        String token =
                Jwts.builder()
                        .setId(usuario.getIdusuario().toString())
                        .setSubject(usuario.getNomusuario())
                        .claim("authorities",
                                authorityList.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList())
                        )
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 300000))
                        .signWith(SignatureAlgorithm.HS512, clave.getBytes())
                        .compact();
        return token;
    }
}
