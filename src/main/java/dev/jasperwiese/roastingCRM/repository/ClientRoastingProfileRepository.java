package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientRoastingProfilesPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRoastingProfileRepository extends JpaRepository<ClientRoastingProfiles, ClientRoastingProfilesPK> {

    List<ClientRoastingProfiles> findClientRoastingProfilesByClientClientId(UUID clientId);

    //custom query.

}
