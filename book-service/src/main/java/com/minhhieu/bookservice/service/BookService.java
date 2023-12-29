package com.minhhieu.bookservice.service;

import com.minhhieu.bookservice.model.Book;
import com.minhhieu.bookservice.request.BookRequest;
import com.minhhieu.bookservice.response.BookResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {


    List<Book> getAllBook(String keyword);
    BookResponse getBookById(long idBook);
    boolean addBook(BookRequest bookRequest, MultipartFile img) throws IOException;
    boolean upDateBook(MultipartFile img,BookRequest bookRequest, long idBook);
    void reduceTotal(long idBook);
    void returnBook (long idBook);

    boolean deleteBook(long idBook);
}
