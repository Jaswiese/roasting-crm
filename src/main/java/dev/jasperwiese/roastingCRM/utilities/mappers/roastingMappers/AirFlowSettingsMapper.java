package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.AirFlowSettingsDto;
import dev.jasperwiese.roastingCRM.entity.AirflowSettings;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AirFlowSettingsMapper {

    public AirflowSettings mapToEntity(AirFlowSettingsDto airFlowSettingsDto) {
        AirflowSettings airflowSettings = new AirflowSettings();
        if(airFlowSettingsDto.getAirFlowSettingsId() != null && !airFlowSettingsDto.getAirFlowSettingsId().isEmpty()){
            airflowSettings.setAirflowSettingsId(UUID.fromString(airFlowSettingsDto.getAirFlowSettingsId()));
        }
        airflowSettings.setLow(airFlowSettingsDto.getLow());
        airflowSettings.setMedium(airFlowSettingsDto.getMedium());
        airflowSettings.setHigh(airFlowSettingsDto.getHigh());
        return airflowSettings;
    }

    public AirFlowSettingsDto mapToDto(AirflowSettings airflowSettings) {
        return AirFlowSettingsDto.builder()
                .airFlowSettingsId(String.valueOf(airflowSettings.getAirflowSettingsId()))
                .low(airflowSettings.getLow())
                .medium(airflowSettings.getMedium())
                .high(airflowSettings.getHigh())
                .build();
    }
}
