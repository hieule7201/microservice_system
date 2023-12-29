package com.minhhieu.bookservice.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private long idBook;
    private String nameBook;
    private String genresBook;
    private int totalBook;
    private String img;
}
