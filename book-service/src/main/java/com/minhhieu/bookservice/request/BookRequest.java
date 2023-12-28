package com.minhhieu.bookservice.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String nameBook;
    private String genresBook;
    private int totalBook;
}
