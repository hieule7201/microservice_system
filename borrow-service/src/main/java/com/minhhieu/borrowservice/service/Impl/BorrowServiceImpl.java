package com.minhhieu.borrowservice.service.Impl;

import com.minhhieu.borrowservice.model.Borrow;
import com.minhhieu.borrowservice.repository.BorrowRepository;
import com.minhhieu.borrowservice.request.BorrowRequest;
import com.minhhieu.borrowservice.response.BookResponse;
import com.minhhieu.borrowservice.response.BorrowResponse;
import com.minhhieu.borrowservice.service.BookService;
import com.minhhieu.borrowservice.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BookService bookService;

    @Override
    public boolean borrowBook(BorrowRequest borrowRequest) {
        bookService.reduceTotal(borrowRequest.getIdBook());
        Borrow borrow = Borrow.builder()
                .statusBorrow("BORROWING")
                .borrowDateStart(new Date())
                .borrowDateEnd(null)
                .idBook(borrowRequest.getIdBook())
                .build();

        borrowRepository.save(borrow);
        return true;
    }

    @Override
    public BorrowResponse showDetail(long idBorrow) {
        Borrow borrow = borrowRepository.findById(idBorrow).orElseThrow(() -> new RuntimeException("Not found"));
        return addBorrowResponse(borrow);
    }

    @Override
    public List<BorrowResponse> getBorrow(String keyword, String statusBorrow) throws ParseException {
        BookResponse bookResponse = new BookResponse();
        List<BorrowResponse> borrowResponseList = new ArrayList<>();
        if (keyword != null && !keyword.isEmpty() && statusBorrow != null && !statusBorrow.isEmpty()){
            borrowRepository.findAllByBorrowDateStartAndStatusBorrow(new SimpleDateFormat("yyyy-MM-dd").parse(keyword),statusBorrow).forEach((item)->{
                borrowResponseList.add(addBorrowResponse(item));
            });
            return borrowResponseList;
        }
        if (keyword != null && !keyword.isEmpty()){
            borrowRepository.findAllByBorrowDateStart(new SimpleDateFormat("yyyy-MM-dd").parse(keyword)).forEach((item)->{
                borrowResponseList.add(addBorrowResponse(item));
            });
            return borrowResponseList;
        }
        if (statusBorrow != null && !statusBorrow.isEmpty()){
            borrowRepository.findAllByStatusBorrow(statusBorrow).forEach((item)->{
                borrowResponseList.add(addBorrowResponse(item));
            });
            return borrowResponseList;
        }
        borrowRepository.findAll().forEach((item)->{
            borrowResponseList.add(addBorrowResponse(item));
        });
        return borrowResponseList;
    }

    @Override
    public boolean giveBackBook(long idBorrow) {
        return borrowRepository.findById(idBorrow).map(item ->{
            bookService.returnTotal(item.getIdBook());
            item.setStatusBorrow("GAVE_BACK");
            item.setBorrowDateEnd(new Date());

            borrowRepository.save(item);
            return true;
        }).orElseThrow(()->new RuntimeException("Not found"));

    }


    BorrowResponse addBorrowResponse(Borrow item){
        return BorrowResponse.builder()
                .statusBorrow(item.getStatusBorrow())
                .idBorrow(item.getIdBorrow())
                .borrowDateStart(item.getBorrowDateStart())
                .borrowDateEnd(item.getBorrowDateEnd())
                .bookResponse(bookService.findBookById(item.getIdBook())).build();

    }
}
