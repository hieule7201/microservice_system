package com.minhhieu.bookservice.controller;

import com.minhhieu.bookservice.model.Book;
import com.minhhieu.bookservice.request.BookRequest;
import com.minhhieu.bookservice.response.BookResponse;
import com.minhhieu.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/get_book")
    public List<Book> getBook(@RequestParam(name = "keyword", required = false) String keyword){
        return bookService.getAllBook(keyword);
    }
    @GetMapping("find_book/{idBook}")
    public BookResponse findBookById(@PathVariable long idBook){
        return bookService.getBookById(idBook);
    }
    @PostMapping("/add_book")
    public boolean addBook(@RequestBody BookRequest bookRequest){
        return bookService.addBook(bookRequest);
    }
    @PutMapping("/update_book/{idBook}")
    public boolean updateBook(@RequestBody BookRequest bookRequest, @PathVariable long idBook){
        return bookService.upDateBook(bookRequest, idBook);
    }
    @PutMapping("/reduce_total/{idBook}")
    public String reduceTotal(@PathVariable long idBook){
        bookService.reduceTotal(idBook);
        return "Successfully";
    }
    @PutMapping("/return_total/{idBook}")
    public String returnTotal(@PathVariable long idBook){
        bookService.returnBook(idBook);
        return "Successfully";
    }
    @DeleteMapping("/delete_book/{idBook}")
    public boolean deleteBook(@PathVariable long idBook){
        return bookService.deleteBook(idBook);
    }
}
