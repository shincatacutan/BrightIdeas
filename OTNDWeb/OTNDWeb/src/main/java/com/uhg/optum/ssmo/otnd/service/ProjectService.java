package com.uhg.optum.ssmo.otnd.service;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Project;

public interface ProjectService {
	public List<Project> getProjects();
	public void addProject(Project project);
}
