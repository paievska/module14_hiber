package org.example.crud;

import org.example.entities.Ticket;

import java.util.List;

public interface TicketCrudService {
    void createTicket(Ticket ticket);

    Ticket getTicketById(int id);

    void updateTicket(Ticket ticket);

    void deleteTicket(int id);
}
