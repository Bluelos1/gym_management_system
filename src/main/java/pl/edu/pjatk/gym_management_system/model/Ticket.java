package pl.edu.pjatk.gym_management_system.model;

import com.sun.istack.NotNull;
import pl.edu.pjatk.gym_management_system.model.enums.TicketCategory;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Start_date")
    private LocalDate startDate;
    @Column(name="End_date")
    private LocalDate endDate;
    @Column(name="Ticket_category")
    @Enumerated(EnumType.STRING)

    private TicketCategory ticketCategory;

    @ManyToMany(mappedBy = "tickets",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Set<Building> buildings=new HashSet<>();

    @OneToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToOne
    @JoinColumn(name = "Client_id")
    private Client client;

    public Ticket(Long id, LocalDate startDate, LocalDate endDate, TicketCategory ticketCategory, Set<Building> buildings, Trainer trainer, Client client) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketCategory = ticketCategory;
        this.buildings = buildings;
        this.trainer = trainer;
        this.client = client;
    }

    public Ticket() {

    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }
}
