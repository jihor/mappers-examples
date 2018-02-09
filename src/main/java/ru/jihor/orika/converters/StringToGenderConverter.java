package ru.jihor.orika.converters;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import ru.jihor.orika.model.target.Gender;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */
public class StringToGenderConverter extends BidirectionalConverter<String, Gender> {

    @Override
    public Gender convertTo(String source, Type<Gender> destinationType, MappingContext mappingContext) {
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
    public String convertFrom(Gender source, Type<String> destinationType, MappingContext mappingContext) {
        return (source != null) ? source.toString() : "unknown";
    }

}
