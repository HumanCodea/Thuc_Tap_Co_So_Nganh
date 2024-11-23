package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.Ticket;
import com.BaiTapLon.repository.TicketRepository;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets(int billId){
        return ticketRepository.findTicketsByBillId(billId);
    }

    public List<Integer> getTicketId(int billId){
        return ticketRepository.findTicketIdByBillId(billId);
    }

    public void saveTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public Integer findSeatIdByTicketId(int ticketId){
        return ticketRepository.findSeatIdByTicketId(ticketId);
    }

    public Integer findScreeningIdByTicketId(int ticketId){
        return ticketRepository.findSreeningIdByTicketId(ticketId);
    }

}
