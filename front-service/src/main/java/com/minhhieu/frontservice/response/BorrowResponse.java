package com.minhhieu.frontservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowResponse {
    private long idBorrow;

    private String statusBorrow;
    private Date borrowDateStart;
    private Date borrowDateEnd;
    private BookResponse bookResponse;
}
