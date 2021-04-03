package dev.vasishta.lld.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class LendBook {
    private int id;
    private Member member;
    private BookItem bookItem;
    private Date lentDate;
    private Date dueDate;
}
