package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.LendBook;

public interface FineService {

    double FINE_PER_DAY = 20;

    boolean collectFine(LendBook lendBook, double amount);
}
