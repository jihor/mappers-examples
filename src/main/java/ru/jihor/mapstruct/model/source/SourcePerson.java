package ru.jihor.mapstruct.model.source;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */
@Data
public class SourcePerson {
    // these fields will be mapped automatically
    String lastName;
    String firstName;
    String middleName;
    LocalDate birthDate;

    // these will require manual mapping
    String comment; // no field with the same name in target model
    String gender; // type mismatch with target model
    Map<LocalDate, String> changes; // no field with the same name and type in target model
}
