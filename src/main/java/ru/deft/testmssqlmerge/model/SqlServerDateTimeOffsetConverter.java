package ru.deft.testmssqlmerge.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This converter can be used when using the sql server DateTimeOffset column.
 * <br/>Simply use a {@link javax.persistence.Convert} with a reference to this class in your entity class over the OffsetDateTime
 * <br/>Inspired from https://stackoverflow.com/questions/44373144/java-8-how-to-create-datetimeformatter-with-milli-micro-or-nano-seconds
 * Created by Mark Cunningham on 2/14/2018
 **/
@Converter(autoApply = true)
public class SqlServerDateTimeOffsetConverter implements AttributeConverter<OffsetDateTime, String> {

    // XXX infers something like -05:00
    // The [.SSSSS] etc are optional mappings to the varied number of nanoseconds. Since sql server can return different ones, we specify a range
    // Note, all precisions must be the "same"
    private static DateTimeFormatter FORMATTER_FROM_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS][.SSSSSSS][.SSSSSS][.SSS][.SS] xxx");
    // OffsetDateTime uses 9 precision always
    private static DateTimeFormatter FORMATTER_TO_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS xxx");

    @Override
    public String convertToDatabaseColumn(OffsetDateTime attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.format(FORMATTER_TO_DB);
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return OffsetDateTime.parse(dbData, FORMATTER_FROM_DB);
    }

}
