package pl.edu.pjatk.gym_management_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.gym_management_system.model.Building;
import pl.edu.pjatk.gym_management_system.model.Client;
import pl.edu.pjatk.gym_management_system.model.Ticket;
import pl.edu.pjatk.gym_management_system.model.Trainer;
import pl.edu.pjatk.gym_management_system.model.enums.Gender;
import pl.edu.pjatk.gym_management_system.model.enums.TicketCategory;
import pl.edu.pjatk.gym_management_system.repository.TicketRepository;
import pl.edu.pjatk.gym_management_system.service.ClientService;
import pl.edu.pjatk.gym_management_system.service.TicketService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    TicketService ticketService;
    @Mock
    TicketRepository ticketRepository;
    @Mock
    ClientService clientService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketService(ticketRepository, clientService);
    }

    @Test
    void Should_CreateTicketWithCorrectInputClientNotExist() {
        //given
        Client a = new Client();
        Ticket expected_ticket = new Ticket(22L, LocalDate.MIN, LocalDate.MAX, TicketCategory.NORMAL, new HashSet<Building>(), new Trainer(), new Client());
        //when
        when(clientService.createClient(a))
                .thenReturn(a);
        when(ticketRepository.save(any()))
                .thenReturn(expected_ticket);
        //then
        assertEquals(expected_ticket, ticketService.createTicket(expected_ticket));
    }

    @Test
    void Should_CreateTicketWithCorrectInputClientExist() {
        //given
        Client a = new Client(21L, "Hubert", "Labuda", 20, Gender.MALE);
        Ticket expected_ticket = new Ticket(22L, LocalDate.MIN, LocalDate.MAX, TicketCategory.NORMAL, new HashSet<Building>(), new Trainer(), new Client());

        //when
        when(clientService.getClientById(a.getId()))
                .thenReturn(a);
        when(ticketRepository.save(any()))
                .thenReturn(expected_ticket);
        //then
        assertEquals(expected_ticket, ticketService.createTicket(expected_ticket));
    }

    @Test
    void Should_ReturnAllTickets() {
        //given
        List<Ticket> tickets_expected = new ArrayList<>();
        tickets_expected.add(new Ticket());
        tickets_expected.add(new Ticket());
        tickets_expected.add(new Ticket());
        //when
        when(ticketRepository.findAll()).thenReturn(tickets_expected);
        //then
        assertEquals(tickets_expected, ticketService.getAllTickets());
    }

    @Test
    void Should_ReturnTicketWithCorrectInput() {
        //given
        Ticket ticket_expected = new Ticket();
        //when
        when(ticketRepository.findById(22L))
                .thenReturn(Optional.of(ticket_expected));
        //then
        assertEquals(ticket_expected, ticketService.getTicketById(22L));
    }


    @Test
    void findTicketByTicketCategory() {
        //given
        List<Ticket> books_expected = new ArrayList<>();
        books_expected.add(new Ticket(22L, LocalDate.MIN, LocalDate.MAX, TicketCategory.NORMAL, new HashSet<Building>(), new Trainer(), new Client()));
        books_expected.add(new Ticket(22L, LocalDate.MIN, LocalDate.MAX, TicketCategory.NORMAL, new HashSet<Building>(), new Trainer(), new Client()));
        books_expected.add(new Ticket(22L, LocalDate.MIN, LocalDate.MAX, TicketCategory.NORMAL, new HashSet<Building>(), new Trainer(), new Client()));
        //when
        when(ticketRepository.findTicketByTicketCategory(TicketCategory.NORMAL))
                .thenReturn(books_expected);
        //then
        assertEquals(books_expected, ticketService.findTicketByTicketCategory(TicketCategory.NORMAL));
    }
}
