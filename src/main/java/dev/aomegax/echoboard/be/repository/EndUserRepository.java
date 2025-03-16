package dev.aomegax.echoboard.be.repository;

import dev.aomegax.echoboard.be.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, String> {

    @Query("select e from EndUser e where e.githubEmail = ?1 or e.googleEmail = ?1")
    Optional<EndUser> findByEmail(String email);
}