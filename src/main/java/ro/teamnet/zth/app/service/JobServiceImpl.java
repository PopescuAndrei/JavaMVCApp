package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.dao.JobDao;
import ro.teamnet.zth.app.domain.Job;

import java.util.List;

/**
 * Created by Andrei on 5/7/2015.
 */
public class JobServiceImpl implements JobService {
    @Override
    public List<Job> findAllJobs() {
        JobDao dao = new JobDao();
        List<Job> list = dao.getAllJobs();
        return list;
    }

    @Override
    public Job getJob(int jobId) {
        return new JobDao().getJobById(jobId);
    }
}
