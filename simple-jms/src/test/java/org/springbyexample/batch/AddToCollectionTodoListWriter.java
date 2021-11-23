/**
 *
 */
package org.springbyexample.batch;

import java.util.List;

import org.springbyexample.batch.domain.TodoList;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.TransactionAwareProxyFactory;



public class AddToCollectionTodoListWriter implements ItemWriter<TodoList> {

	private List<TodoList> todoLists = TransactionAwareProxyFactory.createTransactionalList();

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
	 */
	public void write(List<? extends TodoList> items) throws Exception {
		todoLists.addAll(items);
	}

	public List<TodoList> getTodoLists() {
		return todoLists;
	}

}
