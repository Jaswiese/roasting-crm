package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, UUID> {
}
