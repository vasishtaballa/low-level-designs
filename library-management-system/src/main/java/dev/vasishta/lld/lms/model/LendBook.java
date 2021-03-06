package dev.vasishta.lld.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class LendBook extends BookAction {
    private Date lentDate;
    private Date dueDate;
}
