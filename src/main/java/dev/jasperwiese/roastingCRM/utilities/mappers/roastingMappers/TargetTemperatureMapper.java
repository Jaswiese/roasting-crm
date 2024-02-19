package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.TargetTemperatureDto;
import dev.jasperwiese.roastingCRM.entity.TargetTemperature;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class TargetTemperatureMapper {

    public TargetTemperature mapToEntity(TargetTemperatureDto targetTemperatureDto) {
        //add null check for id handling
        TargetTemperature targetTemperature = new TargetTemperature();
        if(targetTemperatureDto.getTargetTemperatureId() != null && !targetTemperatureDto.getTargetTemperatureId().isEmpty()){
            targetTemperature.setTargetTemperatureId(UUID.fromString(targetTemperatureDto.getTargetTemperatureId()));
        }
        targetTemperature.setPreheat(targetTemperatureDto.getPreheat());
        targetTemperature.setFirstCrack(targetTemperatureDto.getFirstCrack());
        targetTemperature.setDevelopment(targetTemperatureDto.getDevelopment());
        targetTemperature.setDropTemperature(targetTemperatureDto.getDrop());
        return targetTemperature;
    }

    public TargetTemperatureDto mapToDto(TargetTemperature targetTemperature) {
        TargetTemperatureDto targetTemperatureDto = new TargetTemperatureDto();
        targetTemperatureDto.setTargetTemperatureId(targetTemperature.getTargetTemperatureId().toString());
        targetTemperatureDto.setPreheat(targetTemperature.getPreheat());
        targetTemperatureDto.setFirstCrack(targetTemperature.getFirstCrack());
        targetTemperatureDto.setDevelopment(targetTemperature.getDevelopment());
        targetTemperatureDto.setDrop(targetTemperature.getDropTemperature());
        return targetTemperatureDto;
    }
}
