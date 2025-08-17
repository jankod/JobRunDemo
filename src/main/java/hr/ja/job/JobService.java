package hr.ja.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.mappers.JobMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {

    private final ApplicationContext applicationContext;
    private final JobMapper jobMapper;

    public void executeSimpleJob(String message) {
        log.info("Executing simple job with message: {}", message);
        // Ovdje možeš koristiti applicationContext za dohvaćanje drugih bean-ova
        // JobMapper ti omogućava detaljnu kontrolu serialization/deserialization
        System.out.println("JobService: " + message);
    }

    public void executeComplexJob(String message, Integer count) {
        log.info("Executing complex job with message: {} and count: {}", message, count);

        // Pristup Spring bean-ovima kroz context
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        log.info("Available beans count: {}", beanNames.length);

        // Simulacija rada
        for (int i = 0; i < count; i++) {
            System.out.println("Processing item " + (i + 1) + ": " + message);
        }
    }
}

