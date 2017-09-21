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
public class UsuarioController extends AbstractRestController{


    @Autowired private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseRest.object(usuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setSenha(dto.getSenha());
        usuarioRepository.save(usuario);
        return ResponseRest.ok("Usuário salvo com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Usuario usuario) {
        Assert.notNull(usuario, "Usuário não encontrado.");
        return ResponseRest.object(usuario);
    }

    @PutMapping("/email")
    public ResponseEntity<?> buscarEmail(@RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findTopByEmail(dto.getEmail());
        Assert.notNull(usuario, "Email não cadastrado!");
        return ResponseRest.object(usuario);
    }

    @GetMapping("/info/{senha}")
    public ResponseEntity<?> buscarSenha(@PathVariable("senha") Integer senha) {
        Assert.notNull(senha, "A Senha não poder ser vazia");
        Usuario usuario = usuarioRepository.findOneBySenha(senha);
        Assert.notNull(usuario, "Senha incorreta.");
        return ResponseRest.object(usuario);
    }

}
