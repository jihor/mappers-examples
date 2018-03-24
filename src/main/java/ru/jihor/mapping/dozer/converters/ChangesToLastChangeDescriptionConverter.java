package ru.jihor.mapping.dozer.converters;

import one.util.streamex.StreamEx;
import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */

public class ChangesToLastChangeDescriptionConverter extends DozerConverter<Map<LocalDate, String>, String> {

    public ChangesToLastChangeDescriptionConverter() {
        // well that's very elegant :)
        super((Class<Map<LocalDate, String>>) (Class) Map.class, String.class);
    }

    @Override
    public String convertTo(Map<LocalDate, String> source, String destination) {
        // no way to directly separate lastChanged and lastChangedDescription
        Optional<LocalDate> maxDate = (source != null) ?
                StreamEx.ofKeys(source).max(LocalDate::compareTo) :
                Optional.empty();
        return maxDate.map(source::get).orElse("none");

    }

    @Override
    public Map<LocalDate, String> convertFrom(String source, Map<LocalDate, String> destination) {
        // there is no way to know the right date without using a wrapper class
        destination.put(LocalDate.now(), source);
        return destination;
    }
}
