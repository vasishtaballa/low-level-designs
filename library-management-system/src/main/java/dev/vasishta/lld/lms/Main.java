package dev.vasishta.lld.lms;

import dev.vasishta.lld.lms.business.Library;
import dev.vasishta.lld.lms.model.Address;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Library library = getLibrary();
    }

    private static Library getLibrary() {
        return Library.builder()
                .name("The Central Library")
                .address(getAddress())
                .openedOn(new Date(System.currentTimeMillis()))
                .catalog(null)
                .members(null)
                .librarians(null)
                .build();
    }

    private static Address getAddress() {
        return Address.builder()
                .line1("24th block")
                .line2("Near Central University")
                .line3("Brahmapuram")
                .city("Pedana")
                .state("Andhra Pradesh")
                .country("India")
                .pinCode(521366)
                .phoneCode("+91")
                .phoneNumber("8121557815")
                .build();
    }
}
