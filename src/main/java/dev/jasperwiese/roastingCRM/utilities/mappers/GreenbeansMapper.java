package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.GreenBeansDto;
import dev.jasperwiese.roastingCRM.entity.GreenBeans;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GreenbeansMapper {

    public GreenBeans mapToEntity(GreenBeansDto greenBeansDto) {
        GreenBeans greenBeans = new GreenBeans();
        if(greenBeansDto.getGreenBeansId() != null && !greenBeansDto.getGreenBeansId().isEmpty()) {
            greenBeans.setGreenBeansId(UUID.fromString(greenBeansDto.getGreenBeansId()));
        }
        greenBeans.setField(greenBeansDto.getField());
        greenBeans.setRegion(greenBeansDto.getRegion());
        greenBeans.setGrade(greenBeansDto.getGrade());
        greenBeans.setFlavour(greenBeansDto.getFlavour());
        greenBeans.setBody(greenBeansDto.getBody());
        greenBeans.setAcidity(greenBeansDto.getAcidity());
        greenBeans.setProcess(greenBeansDto.getProcess());
        greenBeans.setMoisture(greenBeansDto.getMoisture());
        greenBeans.setPackaging(greenBeansDto.getPackaging());
        greenBeans.setNotes(greenBeansDto.getNotes());
        return greenBeans;
    }

    public  GreenBeansDto mapToDto(GreenBeans greenBeans) {
        return GreenBeansDto.builder()
                .greenBeansId(String.valueOf(greenBeans.getGreenBeansId()))
                .field(greenBeans.getField())
                .region(greenBeans.getRegion())
                .grade(greenBeans.getGrade())
                .flavour(greenBeans.getFlavour())
                .body(greenBeans.getBody())
                .acidity(greenBeans.getAcidity())
                .process(greenBeans.getProcess())
                .moisture(greenBeans.getMoisture())
                .packaging(greenBeans.getPackaging())
                .notes(greenBeans.getNotes())
                .build();
    }
}
