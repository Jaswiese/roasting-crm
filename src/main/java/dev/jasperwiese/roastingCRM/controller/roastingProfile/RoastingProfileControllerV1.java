package dev.jasperwiese.roastingCRM.controller.roastingProfile;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;
import dev.jasperwiese.roastingCRM.service.impl.RoastingProfileServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roasting-profile")
public class RoastingProfileControllerV1 {

    private final RoastingProfileServiceImpl roastingProfileService;

    public RoastingProfileControllerV1(RoastingProfileServiceImpl roastingProfileService) {
        this.roastingProfileService = roastingProfileService;
    }

    @PostMapping("/client/add")
    public ResponseEntity<RoastingProfileDto> addClientRoastingProfile(@RequestBody @Valid ClientAddRoastingProfileRequest clientAddRoastingProfileRequest){
        return new ResponseEntity<>(roastingProfileService.createClientRoastingProfile(clientAddRoastingProfileRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoastingProfileDto>> getAllRoastingProfiles() {
        return new ResponseEntity<>(roastingProfileService.getAllRoastingProfiles(), HttpStatus.OK);
    }

    @GetMapping("/{roastingProfileId}")
    public ResponseEntity<RoastingProfileDto> getRoastingProfileById(@PathVariable String roastingProfileId) {
        return new ResponseEntity<>(roastingProfileService.getRoastingProfileById(roastingProfileId), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RoastingProfileDto>> getClientRoastingProfiles(@PathVariable String clientId) {
        return new ResponseEntity<>(roastingProfileService.getAllRoastingProfilesOfClient(clientId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roastingProfileId}")
    public ResponseEntity<String> deleteRoastingProfileById(@PathVariable String roastingProfileId) {
        roastingProfileService.deleteRoastingProfileById(roastingProfileId);
        return new ResponseEntity<>("Roasting Profile successfully deleted", HttpStatus.OK);
    }
}
