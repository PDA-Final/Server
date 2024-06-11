package com.pda.userapplication.services;

import com.pda.tofinenums.user.Job;
import com.pda.userapplication.services.in.GetJobsUseCase;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JobService implements GetJobsUseCase {
    @Override
    public List<String> getJobs() {
        return Arrays.stream(Job.values())
            .map(Job::toKorean)
            .toList();
    }
}
