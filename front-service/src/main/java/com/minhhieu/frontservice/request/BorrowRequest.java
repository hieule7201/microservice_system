package com.minhhieu.frontservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequest {
    private long idBorrow;
    private String statusBorrow;
    private Date borrowDateStart;
    private Date borrowDateEnd;
    private long idBook;
}
