package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.Member;

public interface BookLendingService {
    boolean lendBook(Member member, Book book);
}
