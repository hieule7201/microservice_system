package com.minhhieu.bookservice.controller;

import com.minhhieu.bookservice.model.Book;
import com.minhhieu.bookservice.request.BookRequest;
import com.minhhieu.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/get_book", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@RequestParam(name = "keyword", required = false) String keyword){
        return ResponseEntity.ok(bookService.getAllBook(keyword));
    }
    @GetMapping("find_book/{idBook}")
    public ResponseEntity<?> findBookById(@PathVariable long idBook){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(idBook));
    }
    @GetMapping("images/{img}")
    public ResponseEntity<?> getImgByName(@PathVariable String img){
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE).body(bookService.getImgByName(img));
    }
    @PostMapping( "/add_book")
    public ResponseEntity<?> addBook(@RequestPart("img") MultipartFile img, @RequestPart("bookRequest") BookRequest bookRequest) throws IOException {
        bookService.addBook(bookRequest, img);
        return ResponseEntity.ok("Successfully");
    }
    @PutMapping("/update_book/{idBook}")
    public String updateBook(@RequestPart("img")MultipartFile img,@RequestPart("bookRequest") BookRequest bookRequest, @PathVariable long idBook){
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
    public ResponseEntity<?> deleteBook(@PathVariable long idBook){
        return ResponseEntity.ok(bookService.deleteBook(idBook));
    }
}
