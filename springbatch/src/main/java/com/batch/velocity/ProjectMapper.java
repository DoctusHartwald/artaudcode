package com.batch.velocity;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;


@Service("projectMapper")
public class ProjectMapper implements FieldSetMapper<Project>{

	public Project mapFieldSet(FieldSet fieldSet) throws BindException {
		int idx  = 0 ;
		Project vProject = new Project();
		vProject.setName(fieldSet.readString(idx));
		vProject.setProject(fieldSet.readString(idx++));
	
		return vProject;
	}

}
