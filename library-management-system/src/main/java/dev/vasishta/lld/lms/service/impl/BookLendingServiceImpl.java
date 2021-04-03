package dev.vasishta.lld.lms.service.impl;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.LendBook;
import dev.vasishta.lld.lms.model.Member;
import dev.vasishta.lld.lms.service.BookLendingService;

import java.util.List;

public class BookLendingServiceImpl implements BookLendingService {
    @Override
    public boolean lendBook(Member member, Book book) {
        return false;
    }

    @Override
    public List<LendBook> getLentBooks(Member member) {
        return null;
    }
}
