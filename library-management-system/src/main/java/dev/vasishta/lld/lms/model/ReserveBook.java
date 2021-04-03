package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReserveBook extends BookAction {
    private Date reserveDate;
    private ReservationStatus reservationStatus;
}
