package ru.jihor.mapping.dozer.converters;

import one.util.streamex.StreamEx;
import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */

public class ChangesToLastChangeConverter extends DozerConverter<Map<LocalDate, String>, LocalDate> {

    public ChangesToLastChangeConverter() {
        // well that's very elegant :)
        super((Class<Map<LocalDate, String>>) (Class) Map.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(Map<LocalDate, String> source, LocalDate destination) {
        // no way to directly separate lastChanged and lastChangedDescription
        return (source != null) ?
                StreamEx.ofKeys(source).max(LocalDate::compareTo).orElse(LocalDate.now()) :
                LocalDate.now();
    }

    @Override
    public Map<LocalDate, String> convertFrom(LocalDate source, Map<LocalDate, String> destination) {
        destination.put(source, "TODO: How to put lastChangedDescription here?");
        return destination;
    }
}
