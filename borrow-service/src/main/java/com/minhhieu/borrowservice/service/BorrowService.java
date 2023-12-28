package com.minhhieu.borrowservice.service;

import com.minhhieu.borrowservice.model.Borrow;
import com.minhhieu.borrowservice.request.BorrowRequest;
import com.minhhieu.borrowservice.response.BorrowResponse;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;

public interface BorrowService {
    boolean borrowBook(BorrowRequest borrowRequest);
    BorrowResponse showDetail(long idBorrow);
    List<BorrowResponse> getBorrow(String keyword, String statusBorrow) throws ParseException;

    boolean giveBackBook(long idBorrow);
}
