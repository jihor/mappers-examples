package ru.jihor.mapping.orika.converters;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import one.util.streamex.StreamEx;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */
public class ChangesToLastChangeConverter extends BidirectionalConverter<Map<LocalDate, String>, LocalDate> {
    @Override
    public LocalDate convertTo(Map<LocalDate, String> source, Type<LocalDate> destinationType, MappingContext mappingContext) {
        return (source != null) ?
               StreamEx.ofKeys(source).max(LocalDate::compareTo).orElse(LocalDate.now()) : LocalDate.now();
    }

    @Override
    public Map<LocalDate, String> convertFrom(LocalDate source, Type<Map<LocalDate, String>> destinationType, MappingContext mappingContext) {
        return Map.of(source, "TODO: How to put lastChangedDescription here?");
    }
}
