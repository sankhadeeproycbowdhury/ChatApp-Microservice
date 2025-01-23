package org.sankha.userservice.repository;

import org.sankha.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.online = :online WHERE u.username = :username")
    void setOnlineByUsername(String username, boolean online);
}
