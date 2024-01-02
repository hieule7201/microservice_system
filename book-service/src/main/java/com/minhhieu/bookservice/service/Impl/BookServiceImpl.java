package com.minhhieu.bookservice.service.Impl;

import com.minhhieu.bookservice.model.Book;
import com.minhhieu.bookservice.repository.BookRepository;
import com.minhhieu.bookservice.request.BookRequest;
import com.minhhieu.bookservice.response.BookResponse;
import com.minhhieu.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Value("${folder.directory}")
    String folder;

    @Override
    public List<Book> getAllBook(String keyword) {
            if (keyword != null) {
                return bookRepository.search(keyword);
            }

        return  bookRepository.findAll();
    }

    @Override
    public BookResponse getBookById(long idBook) {

        return bookRepository.findById(idBook).map(item ->{

                return BookResponse.builder()
                        .idBook(item.getIdBook())
                        .nameBook(item.getNameBook())
                        .genresBook(item.getGenresBook())
                        .totalBook(item.getTotalBook())
                        .img(item.getImg()).build();


        }).orElseThrow(()-> new RuntimeException("Not found"));
    }

    @Override
    public Resource getImgByName(String img) {
        Path imagePath = Paths.get(folder + img);
        try {
             return new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String addBook(BookRequest bookRequest, MultipartFile img) throws IOException {
        String newName = generateNewFileName(img.getOriginalFilename());
        System.out.println(newName);
        Path newImgPath = Path.of(folder, newName);
        Files.copy(img.getInputStream(), newImgPath, StandardCopyOption.REPLACE_EXISTING);
        Book book = Book.builder()
                .nameBook(bookRequest.getNameBook())
                .genresBook(bookRequest.getGenresBook())
                .totalBook(bookRequest.getTotalBook())
                .img(newName).build();
        bookRepository.save(book);
        return "Add Successfully";
    }

    @Override
    public String upDateBook(MultipartFile img, BookRequest bookRequest, long idBook) {

       return bookRepository.findById(idBook).map(item -> {

           try {
               Path newImgPath = Path.of(folder, item.getImg());
               Files.deleteIfExists(newImgPath);
               Files.copy(img.getInputStream(), newImgPath, StandardCopyOption.REPLACE_EXISTING);
               item.setNameBook(bookRequest.getNameBook());
               item.setGenresBook(bookRequest.getGenresBook());
               item.setTotalBook(bookRequest.getTotalBook());
               bookRepository.save(item);
               return "Update Successfully";
           } catch (IOException e) {
               throw new RuntimeException(e);
           }


        }).orElseGet(()-> {

           throw new RuntimeException("not found");
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
    public String deleteBook(long idBook) {
        return bookRepository.findById(idBook).map((item)->{
            Path newImgPath = Path.of(folder, item.getImg());
            try {
                Files.deleteIfExists(newImgPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bookRepository.deleteById(idBook);
            return "Delete Successfully";
        }).orElseThrow(()-> new RuntimeException("not found"));

    }


    private String generateNewFileName(String originalFileName) {

        return System.currentTimeMillis() + getFileExtension(originalFileName);
    }

    private String getFileExtension(String originalFilename) {
        // Extract and return the file extension
        int dotIndex = originalFilename.lastIndexOf(".");
        return dotIndex > 0 ? originalFilename.substring(dotIndex) : "";
    }

}
