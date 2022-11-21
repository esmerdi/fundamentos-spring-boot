package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.usecase.CreateUser;
import com.fundamentos.springboot.fundamentos.usecase.DeleteUser;
import com.fundamentos.springboot.fundamentos.usecase.GetUsers;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.usecase.UpdateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Nos permite que todos los metodos se formateen en json
@RequestMapping("/api/users")
public class UserRestController {
    //create, get, delete, update

    //Casos de Uso.
    private GetUsers getUsers;
    private CreateUser createUser;
    private DeleteUser deleteUser;

    private UpdateUser updateUser;

    public UserRestController(GetUsers getUsers, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUsers = getUsers;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    @GetMapping("/")
    List<User> get(){
        return getUsers.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> createUser(@RequestBody User request){
        return new ResponseEntity<>(createUser.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> udpateUser(@PathVariable User request, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(request, id), HttpStatus.OK);
    }
}
