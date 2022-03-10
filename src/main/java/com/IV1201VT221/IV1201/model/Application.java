package com.IV1201VT221.IV1201.model;

/**
 * Class that holds application information
 */
public class Application {
    private String startDate;
    private String endDate;
    private String[] jobs;
    private float[] experience;

    /**
     * Constructor for application class
     * @param startDate String
     * @param endDate String
     * @param jobs String[]
     * @param experience float[]
     */
    public Application(String[] jobs, float[] experience, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobs = jobs;
        this.experience = experience;
    }

    /**
     * get jobs array
     * @return String[] jobs
     */
    public String[] getJobs() {
        return jobs;
    }

    /**
     * get experience array
     * @return float[] experience
     */
    public float[] getExperience(){
        return experience;
    }

    /**
     * get startdate
     * @return String startdate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * get enddate
     * @return String enddate
     */
    public String getEndDate() {
        return endDate;
    }
}
