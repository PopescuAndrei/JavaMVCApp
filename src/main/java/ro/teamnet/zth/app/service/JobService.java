package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.domain.Employee;
import ro.teamnet.zth.app.domain.Job;

import java.util.List;

/**
 * Created by Andrei on 5/7/2015.
 */
public interface JobService {
    public List<Job> findAllJobs();
    public Job getJob(int jobId);
}
