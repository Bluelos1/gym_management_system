package pl.edu.pjatk.gym_management_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.gym_management_system.model.Trainer;
import pl.edu.pjatk.gym_management_system.model.enums.Gender;
import pl.edu.pjatk.gym_management_system.model.enums.TrainerQualification;
import pl.edu.pjatk.gym_management_system.repository.TrainerRepository;
import pl.edu.pjatk.gym_management_system.service.TrainerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {
    TrainerService trainerService;
    @Mock
    TrainerRepository trainerRepository;

    @BeforeEach
    void setUp() {
        trainerService = new TrainerService(trainerRepository);
    }

    @Test
    void Should_CreateTrainerWithCorrectInput() {
        //given
        Trainer a = new Trainer();
        //when
        when(trainerRepository.save(any())).thenReturn(new Trainer(43L, "Igor", "Podsiadlo",21,Gender.MALE,TrainerQualification.CROSSFIT));
        a = trainerService.createTrainer(a);
        //then
        assertEquals("Igor", a.getFirstName());
        assertEquals("Podsiadlo", a.getLastName());
    }

    @Test
    void Should_ThrowIllegalArgumentExceptionWhenNotFound() {
        //given

        //when
        when(trainerRepository.findTrainerByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(Optional.empty());
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            trainerService.findByNameAndLastName(anyString(), anyString());
        });
    }

    @Test
    void Should_RerunAllTrainers() {
        //given
        List<Trainer> authors_expected = new ArrayList<>();
        authors_expected.add(new Trainer());

        //when
        when(trainerRepository.findAll())
                .thenReturn(authors_expected);
        List<Trainer> authors_actual = trainerService.getAllTrainer();
        //then
        assertEquals(authors_expected, authors_actual);

    }

    @Test
    void Should_ReturnOneTrainersWithCorrectInput() {
        //given
        Trainer author_expected = new Trainer(3L, "Igor", "Podsiadlo",32, Gender.MALE, TrainerQualification.CROSSFIT);
        //when
        when(trainerRepository.getReferenceById(anyLong()))
                .thenReturn(author_expected);
        Trainer author_actual = trainerService.getTrainerById(3L);
        //then
        assertEquals(author_expected, author_actual);
    }
}
