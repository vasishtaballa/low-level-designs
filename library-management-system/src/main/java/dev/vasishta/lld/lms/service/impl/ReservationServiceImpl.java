package dev.vasishta.lld.lms.service.impl;

import dev.vasishta.lld.lms.enums.ReservationStatus;
import dev.vasishta.lld.lms.exception.LMSException;
import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.Member;
import dev.vasishta.lld.lms.model.ReserveBook;
import dev.vasishta.lld.lms.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService {

    private Map<Member, List<ReserveBook>> memToResMap;

    private Map<Book, Member> bookToResMap;

    @Override
    public Member checkForAnyReservation(Book book) {
        if (bookToResMap.containsKey(book))
            return bookToResMap.get(book);
        return null;
    }

    @Override
    public boolean reserveBook(Member member, Book book) {
        ReserveBook reserveBook = new ReserveBook();
        reserveBook.setReserveDate(new Date(System.currentTimeMillis()));
        reserveBook.setBook(book);
        reserveBook.setMember(member);
        reserveBook.setReservationStatus(ReservationStatus.CONFIRMED);
        return false;
    }

    @Override
    public List<ReserveBook> getReservations(Member member) {
        if (memToResMap.containsKey(member))
            return memToResMap.get(member);
        throw new LMSException("You have not lent any books");
    }

    @Override
    public boolean cancelReservation(Member member, Book book) {
        List<ReserveBook> reserveBooks = memToResMap.get(member);
        ReserveBook reserveBook = reserveBooks.stream()
                .filter(item -> (item.getBook().getIsbn() == book.getIsbn() && item.getReservationStatus() == ReservationStatus.CONFIRMED))
                .findAny()
                .get();
        if (reserveBook == null)
            throw new LMSException("You have not reserved for this book. Please try with another book");
        updateMaps(member, book, reserveBook);
        return true;
    }

    private void updateMaps(Member member, Book book, ReserveBook reserveBook) {
        if (memToResMap.containsKey(member))
            memToResMap.get(member).add(reserveBook);
        else {
            List<ReserveBook> reserveBooks = new ArrayList<>();
            reserveBooks.add(reserveBook);
            memToResMap.put(member, reserveBooks);
        }
        if (bookToResMap.containsKey(book))
            throw new LMSException("This item is already lent by somebody. Please try with some other item");
        else
            bookToResMap.put(book, member);
    }
}
