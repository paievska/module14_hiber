package org.example;

import org.example.crud.ClientCrudService;
import org.example.crud.PlanetCrudService;
import org.example.crud.TicketCrudServiceImpl;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.utils.FlywayMigration;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        try (HibernateUtil instance = HibernateUtil.getInstance();
             SessionFactory sessionFactory = instance.getSessionFactory()) {
            FlywayMigration.flywayMigration();
            TicketCrudServiceImpl ticketCrudService = new TicketCrudServiceImpl();
            PlanetCrudService planetCrud = new PlanetCrudService();
            ClientCrudService clientCrud = new ClientCrudService();
            Planet fromPlanet = planetCrud.getById("MARS");
            Planet toPlanet = planetCrud.getById("EAR");
            Client ticketClient = clientCrud.getById(10);

            Ticket newTicket = new Ticket();
            newTicket.setFromPlanet(fromPlanet);
            newTicket.setToPlanet(toPlanet);
            newTicket.setClient(ticketClient);
            ticketCrudService.createTicket(newTicket);
            System.out.println("Add ticket");

            Ticket ticket = ticketCrudService.getTicketById(4);

            ticket.setToPlanet(toPlanet);
            ticketCrudService.updateTicket(ticket);
            System.out.println("Update ticket");

            ticketCrudService.deleteTicket(ticket.getId());
            System.out.println("Delete ticket");
        }
    }
}