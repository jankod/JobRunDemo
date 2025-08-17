package hr.ja.job;

import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    final JobScheduler jobScheduler;
    final JobService jobService;

    @GetMapping("/")
    public String index() {
        return "jobs/job_index";
    }


    @PostMapping("/start")
    public String startJob(@RequestParam(required = false, defaultValue = "Default message") String message) {
        jobScheduler.enqueue(() -> jobService.executeSimpleJob(message));
        System.out.println("Job started successfully.");
        return "jobs/job_index";
    }

    @PostMapping("/start-with-param")
    public String startJobWithParam(@RequestParam String message) {
        jobScheduler.enqueue(() -> jobService.executeSimpleJob(message));
        return "jobs/job_index";
    }

    @PostMapping("/start-complex")
    public String startComplexJob(@RequestParam String message, @RequestParam Integer count) {
        jobScheduler.enqueue(() -> jobService.executeComplexJob(message, count));
        return "jobs/job_index";
    }
}
