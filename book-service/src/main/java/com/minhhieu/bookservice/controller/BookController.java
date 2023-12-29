package com.minhhieu.bookservice.controller;

import com.minhhieu.bookservice.model.Book;
import com.minhhieu.bookservice.request.BookRequest;
import com.minhhieu.bookservice.response.BookResponse;
import com.minhhieu.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<?> findBookById(@PathVariable long idBook){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(idBook));
    }
    @PostMapping( "/add_book")
    public ResponseEntity<?> addBook(@RequestPart("img") MultipartFile img, @RequestPart("bookRequest") BookRequest bookRequest) throws IOException {
        bookService.addBook(bookRequest, img);
        return ResponseEntity.ok("Successfully");
    }
    @PutMapping("/update_book/{idBook}")
    public boolean updateBook(@RequestPart("img")MultipartFile img,@RequestPart("bookRequest") BookRequest bookRequest, @PathVariable long idBook){
        return bookService.upDateBook(img,bookRequest, idBook);
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
