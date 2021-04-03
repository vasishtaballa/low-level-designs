package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.BookStatus;

import java.util.Date;

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
