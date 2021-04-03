package dev.vasishta.lld.lms.service.impl;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.LendBook;
import dev.vasishta.lld.lms.model.Member;
import dev.vasishta.lld.lms.model.ReserveBook;
import dev.vasishta.lld.lms.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService {

    private Map<Member, List<ReserveBook>> memToResMap;
    private Map<Member, List<LendBook>> memToLendMap;
    private Map<Book, List<ReserveBook>> bookToResMap;

    @Override
    public boolean checkForAnyReservation(Book book) {
        return false;
    }

    @Override
    public boolean reserveBook(Member member, Book book) {
        return false;
    }
}
