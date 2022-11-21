package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@Transactional: Si existe algún error a través de este insert, nos permitirá hacer un rollback de todas las transacciones
     * que se realizaron en nuestra base de datos*/
    @Transactional
    public void saveTransactional(List<User> users) {
        users.stream().peek(user -> LOG.info("Usuario insertado: " + user)) //peek para ir mostrando en pantalla.
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User request) {
        return userRepository.save(request);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User request, Long id) {
        return userRepository.findById(id).map(
                user -> {
                    user.setEmail(request.getEmail());
                    user.setBirthDate(request.getBirthDate());
                    user.setName(request.getName());
                    return userRepository.save(user);
                }
        ).get();
    }
}
