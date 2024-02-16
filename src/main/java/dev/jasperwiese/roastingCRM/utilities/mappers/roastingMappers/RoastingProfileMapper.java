package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import dev.jasperwiese.roastingCRM.utilities.mappers.GreenbeansMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoastingProfileMapper {

    private TargetTemperatureMapper targetTemperatureMapper;
    private TimeIntervalsMapper timeIntervalsMapper;
    private  AirFlowSettingsMapper airFlowSettingsMapper;
    private GreenbeansMapper greenbeansMapper;

    public RoastingProfileMapper(TargetTemperatureMapper targetTemperatureMapper,
                                 TimeIntervalsMapper timeIntervalsMapper,
                                 AirFlowSettingsMapper airFlowSettingsMapper,
                                 GreenbeansMapper greenbeansMapper) {
        this.targetTemperatureMapper = targetTemperatureMapper;
        this.timeIntervalsMapper = timeIntervalsMapper;
        this.airFlowSettingsMapper = airFlowSettingsMapper;
        this.greenbeansMapper = greenbeansMapper;
    }

    public RoastingProfile mapToEntity(RoastingProfileDto roastingProfileDto) {
        RoastingProfile roastingProfile = new RoastingProfile();
        if(roastingProfileDto.getRoastingProfileId() != null && !roastingProfileDto.getRoastingProfileId().isEmpty()) {
            roastingProfile.setRoastingProfileId(UUID.fromString(roastingProfileDto.getRoastingProfileId()));
        }
        roastingProfile.setProfileName(roastingProfileDto.getProfileName());
        roastingProfile.setProfileName(roastingProfileDto.getProfileName());
        roastingProfile.setRoasterModel(roastingProfileDto.getRoasterModel());
        //green beans mapper
        roastingProfile.setGreenBeans(greenbeansMapper.mapToEntity(roastingProfileDto.getGreenBeansDto()));
        //temp mapper
        roastingProfile.setTargetTemperature(targetTemperatureMapper.mapToEntity(roastingProfileDto.getTargetTemperatureDto()));
        //airflow mapper
        roastingProfile.setAirflowSettings(airFlowSettingsMapper.mapToEntity(roastingProfileDto.getAirFlowSettingsDto()));
        //time mapper
        roastingProfile.setTimeIntervals(timeIntervalsMapper.mapToEntity(roastingProfileDto.getTimeIntervalsDto()));

        return roastingProfile;
    }

    public RoastingProfileDto mapToDto(RoastingProfile roastingProfile) {
        RoastingProfileDto roastingProfileDto = new RoastingProfileDto();
        roastingProfileDto.setRoastingProfileId(roastingProfile.getRoastingProfileId().toString());
        roastingProfileDto.setProfileName(roastingProfile.getProfileName());
        roastingProfileDto.setRoasterModel(roastingProfile.getRoasterModel());
        roastingProfileDto.setGreenBeansDto(greenbeansMapper.mapToDto(roastingProfile.getGreenBeans()));
        roastingProfileDto.setTargetTemperatureDto(targetTemperatureMapper.mapToDto(roastingProfile.getTargetTemperature()));
        roastingProfileDto.setAirFlowSettingsDto(airFlowSettingsMapper.mapToDto(roastingProfile.getAirflowSettings()));
        roastingProfileDto.setTimeIntervalsDto(timeIntervalsMapper.mapToDto(roastingProfile.getTimeIntervals()));

        return roastingProfileDto;
    }
}
