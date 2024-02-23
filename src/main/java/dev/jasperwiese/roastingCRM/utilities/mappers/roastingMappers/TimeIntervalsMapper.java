package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.TimeIntervalsDto;
import dev.jasperwiese.roastingCRM.entity.TimeIntervals;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TimeIntervalsMapper {

    public TimeIntervals mapToEntity(TimeIntervalsDto timeIntervalsDto) {
        TimeIntervals timeIntervals = new TimeIntervals();
        if(timeIntervalsDto.getTimeIntervalsId() != null && !timeIntervalsDto.getTimeIntervalsId().isEmpty()){
            timeIntervals.setTimeIntervalsId(UUID.fromString(timeIntervalsDto.getTimeIntervalsId()));
        }
        timeIntervals.setPreheat(timeIntervalsDto.getPreheat());
        timeIntervals.setFirstCrack(timeIntervalsDto.getFirstCrack());
        timeIntervals.setDevelopment(timeIntervalsDto.getDevelopment());
        timeIntervals.setTotal(timeIntervalsDto.getTotal());
        return timeIntervals;
    }

    public TimeIntervalsDto mapToDto(TimeIntervals timeIntervals) {
        return TimeIntervalsDto.builder()
                .timeIntervalsId(String.valueOf(timeIntervals.getTimeIntervalsId()))
                .preheat(timeIntervals.getPreheat())
                .firstCrack(timeIntervals.getFirstCrack())
                .development(timeIntervals.getDevelopment())
                .total(timeIntervals.getTotal())
                .build();
    }
}
