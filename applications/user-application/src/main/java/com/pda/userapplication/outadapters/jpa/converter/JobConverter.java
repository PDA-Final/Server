package com.pda.userapplication.outadapters.jpa.converter;

import com.pda.tofinenums.user.Job;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JobConverter implements AttributeConverter<Job, String> {

    @Override
    public String convertToDatabaseColumn(Job job) {
        return job.toKorean();
    }

    @Override
    public Job convertToEntityAttribute(String s) {
        return Job.of(s);
    }
}
