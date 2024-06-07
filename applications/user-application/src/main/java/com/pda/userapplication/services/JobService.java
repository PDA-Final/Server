package com.pda.userapplication.services;

import com.pda.tofinenums.user.Jobs;
import com.pda.userapplication.services.in.GetJobsUseCase;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JobService implements GetJobsUseCase {
    @Override
    public List<String> getJobs() {
        return Arrays.stream(Jobs.values())
            .map(Jobs::toKorean)
            .toList();
    }
}
