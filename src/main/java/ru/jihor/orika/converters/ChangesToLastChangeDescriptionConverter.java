package ru.jihor.orika.converters;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import one.util.streamex.StreamEx;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */
public class ChangesToLastChangeDescriptionConverter extends BidirectionalConverter<Map<LocalDate, String>, String> {
    @Override
    public String convertTo(Map<LocalDate, String> source, Type<String> destinationType, MappingContext mappingContext) {
        // no way to directly separate lastChanged and lastChangedDescription
        Optional<LocalDate> maxDate = (source != null) ?
                                      StreamEx.ofKeys(source).max(LocalDate::compareTo) : Optional.empty();
        return maxDate.map(source::get).orElse("none");
    }

    @Override
    public Map<LocalDate, String> convertFrom(String source, Type<Map<LocalDate, String>> destinationType, MappingContext mappingContext) {
        // there is no way to know the right date without using a wrapper class
        return Map.of(LocalDate.now(), source);
    }
}
