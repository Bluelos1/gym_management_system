package pl.edu.pjatk.gym_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.gym_management_system.model.Ticket;
import pl.edu.pjatk.gym_management_system.model.enums.TicketCategory;
import pl.edu.pjatk.gym_management_system.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ClientService clientService){
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id){
        Optional<Ticket> a = ticketRepository.findById(id);
        if (a.isPresent()){
            return a.get();
        }
        throw new IllegalArgumentException();
    }
    public void deleteTicketById(Long id){
        ticketRepository.deleteById(id);
    }

    public List<Ticket> findTicketByTicketCategory(TicketCategory ticketCategory) {
        return ticketRepository.findTicketByTicketCategory(ticketCategory);
    }
}
