package com.topicosespeciais.quicktask.services;

import com.topicosespeciais.quicktask.domain.QuickTask;
import com.topicosespeciais.quicktask.domain.Usuario;
import com.topicosespeciais.quicktask.exception.UsuarioCadastradoException;
import com.topicosespeciais.quicktask.repository.UsuarioRepository;
import com.topicosespeciais.quicktask.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não encontrado: " + id + ", Tipo: " + QuickTask.class.getName()));
    }
    public void create(Usuario obj) {
        boolean exists = repository.existsByUsername(obj.getUsername());
        if (exists){
            throw new UsuarioCadastradoException(obj.getUsername());
        }
        repository.save(obj);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}
