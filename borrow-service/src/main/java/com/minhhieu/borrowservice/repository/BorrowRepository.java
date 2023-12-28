package com.minhhieu.borrowservice.repository;

import com.minhhieu.borrowservice.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findAllByBorrowDateStartAndStatusBorrow(Date date, String statusBorrow);
    List<Borrow> findAllByStatusBorrow(String statusBorrow);
    List<Borrow> findAllByBorrowDateStart(Date borrowStart);
}
