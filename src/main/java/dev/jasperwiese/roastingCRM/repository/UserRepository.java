package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
