package com.minhhieu.borrowservice.service;


import com.minhhieu.borrowservice.response.BookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;


@FeignClient(name = "book-service", path = "book")
public interface BookService {

    @PutMapping("/reduce_total/{idBook}")
    String reduceTotal(@PathVariable long idBook);

    @GetMapping("/find_book/{idBook}")
    BookResponse findBookById(@PathVariable long idBook);

    @PutMapping("/return_total/{idBook}")
    String returnTotal(@PathVariable long idBook);

}
