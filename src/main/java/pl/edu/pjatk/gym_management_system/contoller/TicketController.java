package pl.edu.pjatk.gym_management_system.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.gym_management_system.model.Ticket;
import pl.edu.pjatk.gym_management_system.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }



    @DeleteMapping
    public ResponseEntity<String> deleteTicketById(@RequestParam("id") Long id){
        ticketService.deleteTicketById(id);
        return ResponseEntity.ok("ok");
    }
}
