package dev.jasperwiese.roastingCRM.controller.roastingProfile;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;
import dev.jasperwiese.roastingCRM.service.impl.RoastingProfileServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roasting-profile")
public class RoastingProfileControllerV1 {

    private final RoastingProfileServiceImpl roastingProfileService;

    public RoastingProfileControllerV1(RoastingProfileServiceImpl roastingProfileService) {
        this.roastingProfileService = roastingProfileService;
    }

    @PostMapping("/client/add")
    public ResponseEntity<RoastingProfileDto> addClientRoastingProfile(@RequestBody ClientAddRoastingProfileRequest clientAddRoastingProfileRequest){
        return new ResponseEntity<>(roastingProfileService.createClientRoastingProfile(clientAddRoastingProfileRequest), HttpStatus.CREATED);

    }

    @GetMapping("/{roastingProfileId}")
    public ResponseEntity<RoastingProfileDto> getRoastingProfileById(@RequestParam String roastingProfileId) {
        return new ResponseEntity<>(roastingProfileService.getRoastingProfileById(roastingProfileId), HttpStatus.OK);
    }
}
