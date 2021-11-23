package com.voleo.entity.document;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public enum DocumentType {
	TEXT(Text.class),
	IMAGE(Image.class),
	VIDEO(Video.class),
	RAWFILE(RawFile.class);
	
	// Champs
	private static Map<String, DocumentType> classMap = 
		new HashMap<String, DocumentType>();

	static {
		for(int i = 0; i < values().length; i++) {
			classMap.put(values()[i].getEntityClass().getName(), values()[i]);
		}
	}
	
	private final Class<? extends Document> docClass;

	
	// Constructeur
	private DocumentType(Class<? extends Document> docClass) {
		this.docClass = docClass;
	}
	
	// Methodes
	public Class<? extends Document> getEntityClass() {
		return docClass;
	}
	
	public static DocumentType getByClass(Class<? extends Document> docClass) {
		return classMap.get(docClass.getName());
	}
	
	public static DocumentType getByClassName(String docClassName) {
		return classMap.get(docClassName);
	}
}
