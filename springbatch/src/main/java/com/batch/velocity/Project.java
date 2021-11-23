package com.batch.velocity;

public class Project {
	
	private String name; 
	private String project;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	} 
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Project [name=");
		builder.append(name);
		builder.append(", project=");
		builder.append(project);
		builder.append("]");
		return builder.toString();
	}
	
}
