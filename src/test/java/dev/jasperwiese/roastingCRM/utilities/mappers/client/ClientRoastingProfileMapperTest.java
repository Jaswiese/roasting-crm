package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.entity.*;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientRoastingProfilesPK;
import dev.jasperwiese.roastingCRM.utilities.mappers.GreenbeansMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.AirFlowSettingsMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.RoastingProfileMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.TargetTemperatureMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.TimeIntervalsMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientRoastingProfileMapperTest {

    private ClientRoastingProfileMapper underTest;
    private List<ClientRoastingProfiles> clientRoastingProfilesList;
    private List<RoastingProfileDto> roastingProfileDtoList;


    @BeforeEach
    void setUp() {
        AirFlowSettingsMapper airFlowSettingsMapper = new AirFlowSettingsMapper();
        TargetTemperatureMapper targetTemperatureMapper = new TargetTemperatureMapper();
        TimeIntervalsMapper timeIntervalsMapper = new TimeIntervalsMapper();
        GreenbeansMapper greenbeansMapper = new GreenbeansMapper();
        RoastingProfileMapper roastingProfileMapper = new RoastingProfileMapper(
                targetTemperatureMapper,
                timeIntervalsMapper,
                airFlowSettingsMapper,
                greenbeansMapper);

        underTest = new ClientRoastingProfileMapper(roastingProfileMapper);

        roastingProfileDtoList = Arrays.asList(
                RoastingProfileDto.builder()
                        .profileName("Afternoon sunset")
                        .roasterModel("LaMazzorco")
                        .notes("Dark roast")
                        .greenBeansDto(
                                GreenBeansDto.builder()
                                        .field("Hen")
                                        .region("Kenya")
                                        .grade("AA")
                                        .flavour("Sweet")
                                        .body("Bold")
                                        .acidity("low")
                                        .process("Barrel")
                                        .moisture("10%")
                                        .packaging("Card")
                                        .notes("ideal for dark roasting")
                                        .build()
                        )
                        .targetTemperatureDto(
                                TargetTemperatureDto.builder()
                                        .preheat("120")
                                        .firstCrack("150")
                                        .development("200")
                                        .build()
                        )
                        .timeIntervalsDto(
                                TimeIntervalsDto.builder()
                                        .preheat("60")
                                        .firstCrack("120")
                                        .development("180")
                                        .total("220")
                                        .build()
                        )
                        .airFlowSettingsDto(
                                AirFlowSettingsDto.builder()
                                        .low("50")
                                        .medium("80")
                                        .high("100")
                                        .build()
                        )
                        .build(),
                RoastingProfileDto.builder()
                        .profileName("Afternoon sunset")
                        .roasterModel("LaMazzorco")
                        .notes("Dark roast")
                        .greenBeansDto(
                                GreenBeansDto.builder()
                                        .field("Hen")
                                        .region("Kenya")
                                        .grade("AA")
                                        .flavour("Sweet")
                                        .body("Bold")
                                        .acidity("low")
                                        .process("Barrel")
                                        .moisture("10%")
                                        .packaging("Card")
                                        .notes("ideal for dark roasting")
                                        .build()
                        )
                        .targetTemperatureDto(
                                TargetTemperatureDto.builder()
                                        .preheat("120")
                                        .firstCrack("150")
                                        .development("200")
                                        .build()
                        )
                        .timeIntervalsDto(
                                TimeIntervalsDto.builder()
                                        .preheat("60")
                                        .firstCrack("120")
                                        .development("180")
                                        .total("220")
                                        .build()
                        )
                        .airFlowSettingsDto(
                                AirFlowSettingsDto.builder()
                                        .low("50")
                                        .medium("80")
                                        .high("100")
                                        .build()
                        )
                        .build()
        );
        clientRoastingProfilesList = Arrays.asList(
                ClientRoastingProfiles.builder()
                        .roastingProfile(
                                RoastingProfile.builder()
                                        .profileName("Afternoon Sunset")
                                        .roasterModel("LaMazzorco")
                                        .greenBeans(
                                                GreenBeans.builder()
                                                        .field("Hen")
                                                        .region("Kenya")
                                                        .grade("AA")
                                                        .flavour("Sweet")
                                                        .body("Bold")
                                                        .acidity("low")
                                                        .process("Barrel")
                                                        .moisture("10%")
                                                        .packaging("Card")
                                                        .notes("ideal for dark roasting")
                                                        .build()
                                        )
                                        .targetTemperature(
                                                TargetTemperature.builder()
                                                        .preheat("120")
                                                        .firstCrack("150")
                                                        .development("200")
                                                        .build()
                                        )
                                        .timeIntervals(
                                                TimeIntervals.builder()
                                                        .preheat("60")
                                                        .firstCrack("120")
                                                        .development("180")
                                                        .total("220")
                                                        .build()
                                        )
                                        .airflowSettings(
                                                AirflowSettings.builder()
                                                        .low("50")
                                                        .medium("80")
                                                        .high("100")
                                                        .build()
                                        )
                                        .build()
                        )
                        .client(
                                Client.builder()
                                        .build()
                        )
                        .profileName("Afternoon Sunset")
                        .notes("Adopted test roast")
                        .build(),
                ClientRoastingProfiles.builder()
                        .roastingProfile(
                                RoastingProfile.builder()
                                        .profileName("Afternoon Sunset")
                                        .roasterModel("LaMazzorco")
                                        .greenBeans(
                                                GreenBeans.builder()
                                                        .field("Hen")
                                                        .region("Kenya")
                                                        .grade("AA")
                                                        .flavour("Sweet")
                                                        .body("Bold")
                                                        .acidity("low")
                                                        .process("Barrel")
                                                        .moisture("10%")
                                                        .packaging("Card")
                                                        .notes("ideal for dark roasting")
                                                        .build()
                                        )
                                        .targetTemperature(
                                                TargetTemperature.builder()
                                                        .preheat("120")
                                                        .firstCrack("150")
                                                        .development("200")
                                                        .build()
                                        )
                                        .timeIntervals(
                                                TimeIntervals.builder()
                                                        .preheat("60")
                                                        .firstCrack("120")
                                                        .development("180")
                                                        .total("220")
                                                        .build()
                                        )
                                        .airflowSettings(
                                                AirflowSettings.builder()
                                                        .low("50")
                                                        .medium("80")
                                                        .high("100")
                                                        .build()
                                        )
                                        .build()
                        )
                        .client(
                                Client.builder()
                                        .build()
                        )
                        .profileName("Afternoon Sunset")
                        .notes("Adopted test roast")
                        .build()
        );
    }

    @Test
    void itShouldMapRoastingProfileDtoListToClientRoastingProfilesList() {
        //Given
        //When
        List<ClientRoastingProfiles> result = underTest.mapRoastingProfileDtoListToClientRoastingProfilesList(roastingProfileDtoList);
        //Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isInstanceOf(ClientRoastingProfiles.class);
        assertThat(result.get(0).getProfileName()).isNullOrEmpty();
        assertThat(result.get(0).getNotes()).isNullOrEmpty();
        assertThat(result.get(0).getRoastingProfile().getProfileName()).isEqualTo(roastingProfileDtoList.get(0).getProfileName());
        assertThat(result.get(0).getRoastingProfile().getRoasterModel()).isEqualTo(roastingProfileDtoList.get(0).getRoasterModel());

        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getField()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getField());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getRegion()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getRegion());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getGrade()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getGrade());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getFlavour()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getFlavour());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getBody()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getBody());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getAcidity()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getAcidity());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getProcess()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getProcess());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getMoisture()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getMoisture());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getPackaging()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getPackaging());
        assertThat(result.get(0).getRoastingProfile().getGreenBeans().getNotes()).isEqualTo(roastingProfileDtoList.get(0).getGreenBeansDto().getNotes());

        assertThat(result.get(0).getRoastingProfile().getTargetTemperature().getPreheat()).isEqualTo(roastingProfileDtoList.get(0).getTargetTemperatureDto().getPreheat());
        assertThat(result.get(0).getRoastingProfile().getTargetTemperature().getFirstCrack()).isEqualTo(roastingProfileDtoList.get(0).getTargetTemperatureDto().getFirstCrack());
        assertThat(result.get(0).getRoastingProfile().getTargetTemperature().getDevelopment()).isEqualTo(roastingProfileDtoList.get(0).getTargetTemperatureDto().getDevelopment());

        assertThat(result.get(0).getRoastingProfile().getTimeIntervals().getPreheat()).isEqualTo(roastingProfileDtoList.get(0).getTimeIntervalsDto().getPreheat());
        assertThat(result.get(0).getRoastingProfile().getTimeIntervals().getFirstCrack()).isEqualTo(roastingProfileDtoList.get(0).getTimeIntervalsDto().getFirstCrack());
        assertThat(result.get(0).getRoastingProfile().getTimeIntervals().getDevelopment()).isEqualTo(roastingProfileDtoList.get(0).getTimeIntervalsDto().getDevelopment());
        assertThat(result.get(0).getRoastingProfile().getTimeIntervals().getTotal()).isEqualTo(roastingProfileDtoList.get(0).getTimeIntervalsDto().getTotal());

        assertThat(result.get(0).getRoastingProfile().getAirflowSettings().getLow()).isEqualTo(roastingProfileDtoList.get(0).getAirFlowSettingsDto().getLow());
        assertThat(result.get(0).getRoastingProfile().getAirflowSettings().getMedium()).isEqualTo(roastingProfileDtoList.get(0).getAirFlowSettingsDto().getMedium());
        assertThat(result.get(0).getRoastingProfile().getAirflowSettings().getHigh()).isEqualTo(roastingProfileDtoList.get(0).getAirFlowSettingsDto().getHigh());
    }

    @Test
    void itShouldMapClientRoastingProfilesListToRoastingProfileDtoList() {
        //Given
        //Setting Ids of the various entities as would be returned from the database.
        UUID roastingProfileId = UUID.fromString("d096b308-ef39-47b4-ac53-a3f294c68a2e");
        UUID clientId = UUID.fromString("94887616-b334-4796-9a82-33f3401af88c");
        ClientRoastingProfilesPK  clientRoastingProfilesPK = new ClientRoastingProfilesPK(roastingProfileId, clientId);
        UUID greenBeansId = UUID.fromString("40069172-bfd4-4a22-8a60-363e8ecc1e52");
        UUID targetTemperatureId = UUID.fromString("875dd5ef-5eb0-4ef1-85d0-22158c16cf25");
        UUID timeIntervalsId = UUID.fromString("de63a855-48b8-4114-9993-5ca5fe327179");
        UUID airflowSettingsId = UUID.fromString("133a22ac-3802-4e12-95a4-fdf8296281b4");
        //Adding Ids to each entry of entities within the ClientRoastingProfiles List, Duplicates ids used here for ease and speed of testing
        for (int i = 0; i < clientRoastingProfilesList.size(); i++) {
            clientRoastingProfilesList.get(i).setId(clientRoastingProfilesPK);
            clientRoastingProfilesList.get(i).getRoastingProfile().setRoastingProfileId(roastingProfileId);
            clientRoastingProfilesList.get(i).getClient().setClientId(clientId);
            clientRoastingProfilesList.get(i).getRoastingProfile().getGreenBeans().setGreenBeansId(greenBeansId);
            clientRoastingProfilesList.get(i).getRoastingProfile().getAirflowSettings().setAirflowSettingsId(airflowSettingsId);
            clientRoastingProfilesList.get(i).getRoastingProfile().getTargetTemperature().setTargetTemperatureId(timeIntervalsId);
            clientRoastingProfilesList.get(i).getRoastingProfile().getTimeIntervals().setTimeIntervalsId(timeIntervalsId);
        }
        //When
        List<RoastingProfileDto> result = underTest.mapClientRoastingProfilesListToRoastingProfileDtoList(clientRoastingProfilesList);
        //Then
        //ClientRoastingProfile mapping specific checking
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getProfileName()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getProfileName());
        assertThat(result.get(0).getRoasterModel()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getRoasterModel());
        assertThat(result.get(0).getNotes()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getNotes());
        //Checking GreenBeans Mapping
        assertThat(result.get(0).getGreenBeansDto().getField()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getField());
        assertThat(result.get(0).getGreenBeansDto().getRegion()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getRegion());
        assertThat(result.get(0).getGreenBeansDto().getGrade()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getGrade());
        assertThat(result.get(0).getGreenBeansDto().getFlavour()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getFlavour());
        assertThat(result.get(0).getGreenBeansDto().getBody()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getBody());
        assertThat(result.get(0).getGreenBeansDto().getAcidity()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getAcidity());
        assertThat(result.get(0).getGreenBeansDto().getProcess()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getProcess());
        assertThat(result.get(0).getGreenBeansDto().getMoisture()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getMoisture());
        assertThat(result.get(0).getGreenBeansDto().getPackaging()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getPackaging());
        assertThat(result.get(0).getGreenBeansDto().getNotes()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getGreenBeans().getNotes());
        //Checking targetTemp mapping
        assertThat(result.get(0).getTargetTemperatureDto().getPreheat()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getTargetTemperature().getPreheat());
        assertThat(result.get(0).getTargetTemperatureDto().getFirstCrack()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getTargetTemperature().getFirstCrack());
        assertThat(result.get(0).getTargetTemperatureDto().getDevelopment()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getTargetTemperature().getDevelopment());
        //Checking TimeIntervals Mapping
        assertThat(result.get(0).getTimeIntervalsDto().getPreheat()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getTimeIntervals().getPreheat());
        assertThat(result.get(0).getTimeIntervalsDto().getFirstCrack()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getTimeIntervals().getFirstCrack());
        assertThat(result.get(0).getTimeIntervalsDto().getDevelopment()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getTimeIntervals().getDevelopment());
        assertThat(result.get(0).getTimeIntervalsDto().getTotal()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getTimeIntervals().getTotal());
        //Checking Airflow Mapping
        assertThat(result.get(0).getAirFlowSettingsDto().getLow()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getAirflowSettings().getLow());
        assertThat(result.get(0).getAirFlowSettingsDto().getMedium()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getAirflowSettings().getMedium());
        assertThat(result.get(0).getAirFlowSettingsDto().getHigh()).isEqualTo(clientRoastingProfilesList.get(0).getRoastingProfile().getAirflowSettings().getHigh());

    }
}