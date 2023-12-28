package com.minhhieu.bookservice.service.Impl;

import com.minhhieu.bookservice.model.Book;
import com.minhhieu.bookservice.repository.BookRepository;
import com.minhhieu.bookservice.request.BookRequest;
import com.minhhieu.bookservice.response.BookResponse;
import com.minhhieu.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBook(String keyword) {
            if (keyword != null) {
                return bookRepository.search(keyword);
            }

        return  bookRepository.findAll();
    }

    @Override
    public BookResponse getBookById(long idBook) {

        return bookRepository.findById(idBook).map(this::copied).orElseThrow(()-> new RuntimeException("Not found"));
    }

    @Override
    public boolean addBook(BookRequest bookRequest) {

        Book book = Book.builder()
                .nameBook(bookRequest.getNameBook())
                .genresBook(bookRequest.getGenresBook())
                .totalBook(bookRequest.getTotalBook()).build();
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean upDateBook(BookRequest bookRequest, long idBook) {
        Book book = Book.builder()
                .nameBook(bookRequest.getNameBook())
                .genresBook(bookRequest.getGenresBook())
                .totalBook(bookRequest.getTotalBook()).build();

       return bookRepository.findById(idBook).map(item -> {
            item.setNameBook(bookRequest.getNameBook());
            item.setGenresBook(bookRequest.getGenresBook());
            item.setTotalBook(bookRequest.getTotalBook());

            bookRepository.save(item);
            return true;
        }).orElseGet(()-> {

           return false;
       });
    }

    @Override
    public void reduceTotal(long idBook) {
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new RuntimeException("Not found"));
        if (book.getTotalBook() < 0 )
            throw new RuntimeException("This book is over");
        book.setTotalBook(book.getTotalBook()-1);
        bookRepository.save(book);
    }

    @Override
    public void returnBook(long idBook) {
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new RuntimeException("Not found"));
        book.setTotalBook(book.getTotalBook()+1);
        bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(long idBook) {

        if (bookRepository.findById(idBook).isEmpty())
            return false;
        bookRepository.deleteById(idBook);
        return true;

    }

    public BookResponse copied(Book book){
        return BookResponse.builder()
                .idBook(book.getIdBook())
                .nameBook(book.getNameBook())
                .genresBook(book.getGenresBook())
                .totalBook(book.getTotalBook()).build();
    }
}
