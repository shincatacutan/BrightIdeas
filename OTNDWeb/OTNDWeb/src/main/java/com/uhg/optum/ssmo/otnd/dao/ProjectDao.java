package com.uhg.optum.ssmo.otnd.dao;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Project;

public interface ProjectDao {
	public List<Project> getProjects();
	public void addProject(Project project);
}
