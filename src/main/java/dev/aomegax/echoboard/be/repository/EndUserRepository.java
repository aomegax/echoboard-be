package dev.aomegax.echoboard.be.repository;

import dev.aomegax.echoboard.be.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, String> {
}