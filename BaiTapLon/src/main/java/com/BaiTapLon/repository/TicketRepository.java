package com.BaiTapLon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    
    @Query("Select t From Ticket t Where t.bill.billId = :billId")
    public List<Ticket> findTicketsByBillId(int billId);
 
}
