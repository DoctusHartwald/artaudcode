package org.springbyexample.batch;

import org.springbyexample.batch.domain.TodoList;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


public class TodoListFieldSetMapper implements FieldSetMapper<TodoList> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet(org.springframework.batch.item.file.mapping.FieldSet)
	 */
	public TodoList mapFieldSet(FieldSet fs) {
		TodoList todoList = new TodoList();
		todoList.setListId(fs.readString("id"));
		todoList.setName(fs.readString("name"));
		todoList.setRssAllowed(fs.readBoolean("rssAllowed"));
		todoList.setLastUpdate(fs.readDate("lastUpdate"));
		return todoList;
	}

}