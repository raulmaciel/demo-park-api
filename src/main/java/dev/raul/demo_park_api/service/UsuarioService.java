package dev.raul.demo_park_api.service;

import dev.raul.demo_park_api.entity.Usuario;
import dev.raul.demo_park_api.exception.EntityNotFoundException;
import dev.raul.demo_park_api.exception.PasswordInvalidException;
import dev.raul.demo_park_api.exception.UsernameUniqueViolationException;
import dev.raul.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        }catch (DataIntegrityViolationException e){
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado!", usuario.getUsername()));
        }
    }


    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id= %s não encontrado!", id))
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if(!novaSenha.equals(confirmaSenha)){
            throw new PasswordInvalidException("As senhas não são iguais.");
        }

        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)){
            throw new PasswordInvalidException("Sua senha não confere!");
        }

        user.setPassword(novaSenha);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
