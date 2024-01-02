package com.minhhieu.borrowservice.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private long idBook;
    private String nameBook;
    private String genresBook;
    private int totalBook;
    private String img;
}
