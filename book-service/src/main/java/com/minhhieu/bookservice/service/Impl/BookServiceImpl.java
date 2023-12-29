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
    public boolean addBook(BookRequest bookRequest, MultipartFile img) throws IOException {
        String newName = generateNewFileName(img.getOriginalFilename());

        String filePath = folder+newName;
        Path newImgPath = Path.of(folder, getLastSegment(newName));
        Files.copy(img.getInputStream(), newImgPath, StandardCopyOption.REPLACE_EXISTING);
        Book book = Book.builder()
                .nameBook(bookRequest.getNameBook())
                .genresBook(bookRequest.getGenresBook())
                .totalBook(bookRequest.getTotalBook())
                .img(filePath).build();
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean upDateBook(MultipartFile img, BookRequest bookRequest, long idBook) {

       return bookRepository.findById(idBook).map(item -> {

           try {
               String filePath = folder+getLastSegment(item.getImg());
               Path newImgPath = Path.of(folder, getLastSegment(item.getImg()));
               Files.deleteIfExists(newImgPath);
               img.transferTo(new File(filePath));
               item.setNameBook(bookRequest.getNameBook());
               item.setGenresBook(bookRequest.getGenresBook());
               item.setTotalBook(bookRequest.getTotalBook());
               item.setImg(filePath);
               bookRepository.save(item);
               return true;
           } catch (IOException e) {
               throw new RuntimeException(e);
           }


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


    private String generateNewFileName(String originalFileName) {

        return System.currentTimeMillis() + getFileExtension(originalFileName);
    }
    private String getLastSegment(String fullPath) {
        int lastSlashIndex = fullPath.lastIndexOf('/');
        if (lastSlashIndex >= 0 && lastSlashIndex < fullPath.length() - 1) {
            return fullPath.substring(lastSlashIndex + 1);
        } else {
            // No '/' found or the last character is '/', return the original path
            return fullPath;
        }
    }
    private String getFileExtension(String originalFilename) {
        // Extract and return the file extension
        int dotIndex = originalFilename.lastIndexOf(".");
        return dotIndex > 0 ? originalFilename.substring(dotIndex) : "";
    }

}
