package com.mitrais.rms.model;

public class Education {
	private Long educationId;
	private Long userId;
	private String period;
	private String qualification;
	private String school;
	public Education(Long educationId, Long userId, String period, String qualification, String school) {
		super();
		this.educationId = educationId;
		this.userId = userId;
		this.period = period;
		this.qualification = qualification;
		this.school = school;
	}
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
}
