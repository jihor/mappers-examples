package ru.jihor.selma.mappers;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.Mapper;
import one.util.streamex.StreamEx;
import ru.jihor.selma.model.source.SourcePerson;
import ru.jihor.selma.model.target.Gender;
import ru.jihor.selma.model.target.TargetPerson;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-09
 */
@Mapper(withCustomFields = {
        @Field({"comment", "description"}),
        @Field({"changes", "lastChanged"})},
        withIgnoreFields = {"ru.jihor.selma.model.target.TargetPerson.lastchangeddescription"}
)
public abstract class PersonMapper {
    public abstract TargetPerson asTargetPerson(SourcePerson source);

    public Gender mapGender(String gender) {
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

    public LocalDate mapChanges(Map<LocalDate, String> changes) {
        return (changes != null) ?
               StreamEx.ofKeys(changes).max(LocalDate::compareTo).orElse(LocalDate.now()) : LocalDate.now();
    }

    /**
     * Method for additional mappings not handled by Selma.
     *
     * This method must be static, else it will be used in class generation and invoked from the asTargetPerson(SourcePerson source) instead of all the default mappers.
     */
    public static TargetPerson applyPostMapping(SourcePerson source, TargetPerson target){
        Map<LocalDate, String> changes = source.getChanges();
        Optional<LocalDate> maxDate = (changes != null) ?
                                      StreamEx.ofKeys(changes).max(LocalDate::compareTo) : Optional.empty();
        target.setLastChangedDescription(maxDate.map(changes::get).orElse("none"));

        return target;
    }
}
