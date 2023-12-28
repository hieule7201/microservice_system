package com.minhhieu.borrowservice.request;

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
public class BorrowRequest {

    private String statusBorrow;
    private Date borrowDateStart;
    private Date borrowDateEnd;
    private long idBook;
}
