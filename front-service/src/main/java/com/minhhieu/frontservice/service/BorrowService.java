package com.minhhieu.frontservice.service;


import com.minhhieu.frontservice.request.BorrowRequest;
import com.minhhieu.frontservice.response.BorrowResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@FeignClient(name = "borrow-service", url = "http://localhost:8090/borrow")
public interface BorrowService {
    @RequestMapping("/getBorrow")
    List<BorrowResponse> getBorrow(@RequestParam(value = "keyword" , required = false) String keyword, @RequestParam(value = "statusBorrow" , required = false) String statusBorrow);
    @GetMapping("/get_borrow_detail/{idBorrow}")
    BorrowResponse getBorrowDetail(@PathVariable long idBorrow);
    @PostMapping("/borrow_book")
    boolean borrowBook (@RequestBody BorrowRequest borrowRequest);
    @DeleteMapping("/give_back/{idBorrow}")
    boolean giveBackBook(@PathVariable long idBorrow);
}
