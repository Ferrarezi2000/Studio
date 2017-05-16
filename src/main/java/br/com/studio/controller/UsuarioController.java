package br.com.studio.controller;

import br.com.studio.dto.UsuarioDTO;
import br.com.studio.model.ResponseRest;
import br.com.studio.model.Usuario;
import br.com.studio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<?> listar() {

        return ResponseRest.object(usuarioRepository.findAll());
    }



    @PostMapping
    public ResponseEntity<?> save(@RequestBody UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        usuarioRepository.save(usuario);


        return ResponseRest.ok("Usuário salvo com sucesso!");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Usuario usuario) {
        Assert.notNull(usuario, "Usuário não encontrado.");
        return ResponseRest.object(usuario);
    }


}
