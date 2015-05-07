package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.ParameterAnnotation;
import ro.teamnet.zth.app.domain.Job;
import ro.teamnet.zth.app.service.JobServiceImpl;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrei on 5/6/2015.
 */
@MyController(urlPath = "/jobs")
public class JobsController {

    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public List<Job> getAllJobs() {
        JobServiceImpl jobService = new JobServiceImpl();
        return jobService.findAllJobs();
    }

    @MyRequestMethod(urlPath = "/one", methodType = "GET")
    public Job getOneJob(@ParameterAnnotation(parameterName = "jobId")String jobId) {
        JobServiceImpl jobService = new JobServiceImpl();
        return jobService.getJob(Integer.parseInt(jobId));
    }
}
