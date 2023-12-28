package com.minhhieu.frontservice.service;

import com.minhhieu.frontservice.request.BookRequest;
import com.minhhieu.frontservice.response.BookResponse;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:8090/book")
public interface BookService {


    @GetMapping("/find_book/{idBook}")
    BookResponse findBookById(@PathVariable long idBook);
    @RequestMapping("get_book")
    List<?> getBook(@RequestParam(name = "keyword") String keyword);
    @PostMapping("add_book")
    boolean addBook(@RequestBody BookRequest bookRequest);
    @PutMapping("/update_book/{idBook}")
    boolean updateBook(@RequestBody BookResponse bookResponse, @PathVariable long idBook);
    @DeleteMapping("/delete_book/{idBook}")
    boolean deleteBook(@PathVariable long idBook);
}
