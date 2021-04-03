package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BookItem extends Book {
    private int id;
    private String barcode;
    private BookStatus bookStatus;
    private Date dateOfPurchase;
    private boolean refOnly;
    private Double price;
    private Date publicationDate;
    private Rack placedAt;
}
