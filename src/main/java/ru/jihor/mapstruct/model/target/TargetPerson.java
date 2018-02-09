package ru.jihor.mapstruct.model.target;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */
@Data
public class TargetPerson {
    String lastName;
    String firstName;
    String middleName;
    LocalDate birthDate;

    String description;
    Gender gender;
    LocalDate lastChanged;
    String lastChangedDescription;
}
