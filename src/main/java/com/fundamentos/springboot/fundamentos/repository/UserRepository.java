package com.fundamentos.springboot.fundamentos.repository;


import com.fundamentos.springboot.fundamentos.dto.UserDTO;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    /*Consultamos todos los usuarios que empiecen por el nombre que le pasemos por parametro. En la implementación
    * los ordenamos por id */
    @Query("Select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //Query Methods
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDTO(u.id, u.name, u.birthDate)" +
    " FROM User u" +
    " where u.birthDate=:parameterDate" +
    " and u.email=:parameterEmail")
    Optional<UserDTO> getAllByBirthDateAndEmail(@Param("parameterDate") LocalDate date, @Param("parameterEmail") String email);
}
