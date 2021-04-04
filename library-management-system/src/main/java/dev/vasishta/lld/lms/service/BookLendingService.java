package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.LendBook;
import dev.vasishta.lld.lms.model.Member;

import java.util.List;

public interface BookLendingService {
    boolean lendBook(Member member, Book book);

    List<LendBook> getLentBooks(Member member);

    boolean returnBook(Member member, Book book);
}
