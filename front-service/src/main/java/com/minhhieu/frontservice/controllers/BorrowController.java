package com.minhhieu.frontservice.controllers;

import com.minhhieu.frontservice.request.BookRequest;
import com.minhhieu.frontservice.request.BorrowRequest;
import com.minhhieu.frontservice.response.BookResponse;
import com.minhhieu.frontservice.response.BorrowResponse;
import com.minhhieu.frontservice.service.BookService;
import com.minhhieu.frontservice.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("front")
public class BorrowController {
    @Autowired
    BorrowService borrowService;
    @Autowired
    BookService bookService;
    String url="http://localhost:8090/front";

    @RequestMapping("/getBorrow")
    public String borrows(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "statusBorrow", required = false) String statusBorrow, Model model) throws ParseException {

        List<BorrowResponse> borrow = borrowService.getBorrow(keyword, statusBorrow);

        model.addAttribute("borrows", borrow);
        model.addAttribute("keyword",keyword);
        model.addAttribute("statusBorrow",statusBorrow);
        return "borrow/borrows";
    }
    @GetMapping("/give_back_book/{idBorrow}")
    public String giveBackBook(@PathVariable long idBorrow, RedirectAttributes redirectAttributes){
        if (borrowService.giveBackBook(idBorrow)) {
            redirectAttributes.addFlashAttribute("message", "Give Back Success");
            return "redirect:"+url+"/getBorrow";
        }

        redirectAttributes.addFlashAttribute("message", "Give Back Failure");
        return "redirect:"+url+"/getBorrow";
    }
    @GetMapping("/borrowAdd")
    public String showBorrowAddForm(Model model){
        List<?> books = bookService.getBook(null);
        model.addAttribute("books", books);
        model.addAttribute("borrow", new BorrowRequest());
        return "borrow/borrowAdd";
    }
    @PostMapping("/save_borrow_book")
    public String borrowBook(@ModelAttribute("borrow") BorrowRequest borrowRequest, RedirectAttributes redirectAttributes){
        if(borrowService.borrowBook(borrowRequest)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            System.out.println(borrowRequest);
            return "redirect:"+url+"/getBorrow";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:"+url+"/borrowAdd";
    }

}
