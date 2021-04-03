package dev.vasishta.lld.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookAction {
    private int id;
    private Member member;
    private Book book;
}
