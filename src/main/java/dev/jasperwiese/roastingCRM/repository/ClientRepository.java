package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

}
