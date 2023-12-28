package com.minhhieu.borrowservice.controller;


import com.minhhieu.borrowservice.model.Borrow;
import com.minhhieu.borrowservice.request.BorrowRequest;
import com.minhhieu.borrowservice.response.BorrowResponse;
import com.minhhieu.borrowservice.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @RequestMapping("/getBorrow")
    public List<BorrowResponse> getBorrow(@RequestParam(name = "keyword", required = false) String keyword, @RequestParam(value = "statusBorrow" ,required = false) String statusBorrow) throws ParseException {
        return borrowService.getBorrow(keyword, statusBorrow);
    }
    @GetMapping("/get_borrow_detail/{idBorrow}")
    public BorrowResponse getBorrowDetail(@PathVariable long idBorrow){
        return borrowService.showDetail(idBorrow);
    }
    @PostMapping("/borrow_book")
    public boolean borrowBook (@RequestBody BorrowRequest borrowRequest){
        return borrowService.borrowBook(borrowRequest);

    }
    @DeleteMapping("/give_back/{idBorrow}")
    public boolean giveBackBook(@PathVariable long idBorrow){
        return borrowService.giveBackBook(idBorrow);
    }
}
