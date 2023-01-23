package pl.edu.pjatk.gym_management_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.gym_management_system.model.Client;
import pl.edu.pjatk.gym_management_system.model.enums.Gender;
import pl.edu.pjatk.gym_management_system.repository.ClientRepository;
import pl.edu.pjatk.gym_management_system.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {


        ClientService clientService;
        @Mock
        ClientRepository clientRepository;

        @BeforeEach
        void setUp() {
            clientService = new ClientService(clientRepository);
        }

        @Test
        void Should_CreateClientWithCorrectInput() {
            //given
            Client a = new Client();
            //when
            when(clientRepository.save(any())).thenReturn(new Client(21L, "Hubert", "Labuda",20, Gender.MALE));
            a = clientService.createClient(a);
            //then
            assertEquals("Hubert", a.getFirstName());
            assertEquals("Labuda", a.getLastName());
        }

        @Test
        void Should_ThrowIllegalArgumentExceptionWhenNotFound() {
            //given

            //when
            when(clientRepository.findClientByFirstNameAndLastName(anyString(), anyString()))
                    .thenReturn(Optional.empty());
            //then
            assertThrows(IllegalArgumentException.class, () -> {
                clientService.findByNameAndLastName(anyString(), anyString());
            });
        }

        @Test
        void Should_RerunAllClients() {
            //given
            List<Client> authors_expected = new ArrayList<>();
            authors_expected.add(new Client());
            authors_expected.add(new Client());
            authors_expected.add(new Client());
            //when
            when(clientRepository.findAll())
                    .thenReturn(authors_expected);
            List<Client> authors_actual = clientService.getAllClients();
            //then
            assertEquals(authors_expected, authors_actual);

        }

        @Test
        void Should_ReturnOneClientWithCorrectInput() {
            //given
            Client author_expected = new Client(21L, "Hubert", "Labuda",21,Gender.MALE);
            //when
            when(clientRepository.getReferenceById(anyLong()))
                    .thenReturn(author_expected);
            Client author_actual = clientService.getClientById(21L);
            //then
            assertEquals(author_expected, author_actual);
        }

    }

