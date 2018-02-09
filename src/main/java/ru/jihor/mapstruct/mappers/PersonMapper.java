package ru.jihor.mapstruct.mappers;

import one.util.streamex.StreamEx;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.jihor.mapstruct.model.source.SourcePerson;
import ru.jihor.mapstruct.model.target.Gender;
import ru.jihor.mapstruct.model.target.TargetPerson;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-09
 */
@Mapper
public interface PersonMapper {

    @Mappings({
            @Mapping(source = "comment", target = "description"),
            @Mapping(source = "changes", target = "lastChanged"),
            @Mapping(source = "changes", target = "lastChangedDescription"),
    })
    TargetPerson sourcePersonToTargetPerson(SourcePerson sourcePerson);

    @Mapping(source = "gender", target = "gender")
    default Gender mapGender(String gender) {
        if (gender == null) {
            return Gender.UNKNOWN;
        }
        switch (gender.toLowerCase()) {
            case "male":
                return Gender.MALE;
            case "female":
                return Gender.FEMALE;
            default:
                return Gender.OTHER;
        }
    }

    @Mapping(source = "changes", target = "lastChanged")
    default LocalDate mapChangesToLastChanged(Map<LocalDate, String> changes) {
        return (changes != null) ?
               StreamEx.ofKeys(changes).max(LocalDate::compareTo).orElse(LocalDate.now()) : LocalDate.now();
    }

    @Mapping(source = "changes", target = "lastChangedDescription")
    default String mapChangesToLastChangedDesctiprion(Map<LocalDate, String> changes) {
        Optional<LocalDate> maxDate = (changes != null) ?
                                      StreamEx.ofKeys(changes).max(LocalDate::compareTo) : Optional.empty();
        return maxDate.map(changes::get).orElse("none");
    }

}
