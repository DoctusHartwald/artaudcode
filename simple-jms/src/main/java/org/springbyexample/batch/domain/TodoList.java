package org.springbyexample.batch.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




public class TodoList  {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 4048798961366546485L;

    private String listId;

    private String name;

    private boolean rssAllowed;

    private Date lastUpdate;


    public TodoList() {
    	super();
    }

    public TodoList(String listId, String name, boolean rssAllowed,
			Date lastUpdate) {
		this();
		this.listId = listId;
		this.name = name;
		this.rssAllowed = rssAllowed;
		this.lastUpdate = lastUpdate;
	}



    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRssAllowed() {
        return rssAllowed;
    }

    public void setRssAllowed(boolean rssAllowed) {
        this.rssAllowed = rssAllowed;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoList)) {
            return false;
        }

        final TodoList todoList = (TodoList) o;

        if (listId != null ? !listId.equals(todoList.listId)
                : todoList.listId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (listId != null ? listId.hashCode() : 0);
    }

	public int compareTo(TodoList o) {
		// TODO Auto-generated method stub
		return 0;
	}
}