package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactPersonRepository extends JpaRepository<ContactPerson, UUID> {

}
