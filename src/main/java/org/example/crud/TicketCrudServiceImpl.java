package org.example.crud;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.entities.Ticket;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class TicketCrudServiceImpl implements TicketCrudService{
    @Override
    public void createTicket(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getClient().getId() == 0 ||
        ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new IllegalArgumentException("Invalid client or planet information for the ticket");
        }

        try (HibernateUtil instance = HibernateUtil.getInstance()) {
            SessionFactory sessionFactory = instance.getSessionFactory();
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                ticket.setCreatedAt(LocalDateTime.now());
                session.persist(ticket);
                transaction.commit();
            }
        }
    }

    @Override
    public Ticket getTicketById(int id) {
        try (HibernateUtil instance = HibernateUtil.getInstance()) {
            SessionFactory sessionFactory = instance.getSessionFactory();
            try (Session session = sessionFactory.openSession()) {
                return session.get(Ticket.class, id);
            }
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        try (HibernateUtil instance = HibernateUtil.getInstance()) {
            SessionFactory sessionFactory = instance.getSessionFactory();
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.merge(ticket);
                transaction.commit();
            }
        }
    }

    @Override
    public void deleteTicket(int id) {
        try (HibernateUtil instance = HibernateUtil.getInstance()) {
            SessionFactory sessionFactory = instance.getSessionFactory();
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                Ticket ticket = session.get(Ticket.class, id);
                if (ticket != null) {
                    session.delete(ticket);
                }
                transaction.commit();
            }
        }
    }
}
