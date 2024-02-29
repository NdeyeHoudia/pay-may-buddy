package com.openclassrooms.paymaybuddy.repository;

import com.openclassrooms.paymaybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    /*@Query("select u.email from user u where u.parent is not null")
    List<User> chercherParent(@Param("parent") String mc, Pageable pageable);

    @Query("select u.email from user u where u.parent is not null")
    List<User> listFrient(@Param("parent") User friend);

     */
}
