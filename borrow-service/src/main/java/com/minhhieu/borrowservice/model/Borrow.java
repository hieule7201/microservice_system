package com.minhhieu.borrowservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrow")
@Builder
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBorrow;
    private long idUser;
    private String statusBorrow;
    @Temporal(TemporalType.DATE)
    private Date borrowDateStart;
    @Temporal(TemporalType.DATE)
    private Date borrowDateEnd;
    private long idBook;

}
