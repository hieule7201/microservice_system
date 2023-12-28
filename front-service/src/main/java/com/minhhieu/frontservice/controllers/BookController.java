package com.minhhieu.frontservice.controllers;


import com.minhhieu.frontservice.request.BookRequest;
import com.minhhieu.frontservice.response.BookResponse;
import com.minhhieu.frontservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("front")
public class BookController {
    @Autowired
    BookService bookService;


    String url = "http://localhost:8090/front";

    @RequestMapping("/getBook")
    public String books(@RequestParam(name = "keyword", required = false) String keyword, Model model){
        List<?> book = bookService.getBook(keyword);
        model.addAttribute("books", book);
        model.addAttribute("keyword", keyword);
        return "book/books";
    }
    @GetMapping("/bookAdd")
    public String showAddBookForm(Model model){
        model.addAttribute("book", new BookRequest());
        return "book/bookAdd";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") BookRequest bookRequest, RedirectAttributes redirectAttributes){
        if (bookRequest.getNameBook() == null || bookRequest.getTotalBook() <1 || bookRequest.getGenresBook() == null) {
            redirectAttributes.addFlashAttribute("message", "Save Failure");
            return "redirect:"+url+"/bookAdd";
        }
        if(bookService.addBook(bookRequest)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            System.out.println(bookRequest);
            return "redirect:"+url+"/getBook";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:"+url+"/bookAdd";
    }
    @GetMapping("/bookEdit/{idBook}")
    public String showUpdateBookForm(@PathVariable long idBook, Model model){
        model.addAttribute("book", bookService.findBookById(idBook));
        return "book/bookEdit";
    }
    @PostMapping("/save-edit-book")
    public String saveEdit(@ModelAttribute("book") BookResponse bookResponse, RedirectAttributes redirectAttributes){
        if(bookService.updateBook(bookResponse, bookResponse.getIdBook())) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:"+url+"/getBook";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:"+url+"/bookEdit/" +bookResponse.getIdBook();
    }
    @GetMapping("/book-delete/{idBook}")
    public String deleteBook(@PathVariable long idBook, RedirectAttributes redirectAttributes){
        if (bookService.deleteBook(idBook)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:"+url+"/getBook";
        }

        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:"+url+"/getBook";
    }
}
