package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, UUID> {
}
