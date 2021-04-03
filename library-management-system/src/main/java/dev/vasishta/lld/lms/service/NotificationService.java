package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.Member;

public interface NotificationService {
    boolean sendNotification(Member member, Book book);
}
