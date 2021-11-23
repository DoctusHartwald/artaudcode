package com.voleo.entity.bridge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.search.bridge.StringBridge;
import org.hibernate.search.bridge.TwoWayStringBridge;

import com.voleo.entity.document.Tag;

public class TagCollectionStringBridge implements StringBridge, TwoWayStringBridge  {

	@SuppressWarnings("unchecked")
	@Override
	public String objectToString(Object o) {
		StringBuilder builder = new StringBuilder();
		Collection<Tag> tags = (Collection<Tag>)o;
		for (Tag tag : tags) {
			builder.append(tag.getName());
			builder.append(' ');
		}
		return builder.toString();
	}

	@Override
	public Object stringToObject(String s) {
		List<String> strings = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(s);
		
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			strings.add(token);
		}
		return strings;
	}

}
