package dev.vasishta.lld.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IDCard {
    private int id;
    private String barcode;
    private Account account;
}
