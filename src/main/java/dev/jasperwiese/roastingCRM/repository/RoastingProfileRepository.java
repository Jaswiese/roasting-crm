package dev.jasperwiese.roastingCRM.repository;

import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoastingProfileRepository extends JpaRepository<RoastingProfile, UUID> {
}
