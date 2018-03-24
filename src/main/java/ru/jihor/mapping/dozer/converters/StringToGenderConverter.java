package ru.jihor.mapping.dozer.converters;

import org.dozer.DozerConverter;
import ru.jihor.model.target.Gender;

import java.util.Optional;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */
public class StringToGenderConverter extends DozerConverter<String, Gender> {
    public StringToGenderConverter() {
        super(String.class, Gender.class);
    }

    @Override
    public Gender convertTo(String source, Gender destination) {
        if (source == null) {
            return Gender.UNKNOWN;
        }
        switch (source.toLowerCase()) {
            case "male":
                return Gender.MALE;
            case "female":
                return Gender.FEMALE;
            default:
                return Gender.OTHER;
        }
    }

    @Override
    public String convertFrom(Gender source, String destination) {
        return Optional.ofNullable(source)
                       .map(Gender::toString)
                       .orElse("unknown");
    }
}
