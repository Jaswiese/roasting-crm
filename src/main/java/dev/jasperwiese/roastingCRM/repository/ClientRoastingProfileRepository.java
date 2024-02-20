package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientRoastingProfilesPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRoastingProfileRepository extends JpaRepository<ClientRoastingProfiles, ClientRoastingProfilesPK> {
}
