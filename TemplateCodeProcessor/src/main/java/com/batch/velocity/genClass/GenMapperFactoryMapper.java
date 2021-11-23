package com.batch.velocity.genClass;

import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import com.batch.velocity.genClass.GenMapperFactory;

/**
 * 
 * @author artaud
 *
 */
@Service("genMapperFactoryMapper")
public class GenMapperFactoryMapper implements FieldSetMapper<GenMapperFactory> {

	
	public GenMapperFactory mapFieldSet(FieldSet fs) throws BindException {
		GenMapperFactory item = new GenMapperFactory();

		int idx  = 0 ;
		
		item.setChamp1(fs.readString("champ1"));
		item.setData1Type(fs.readString("data1Type"));
		item.setDocumentation1(fs.readString("documentation1"));
		System.out.println("1:idx: > "+idx+"  " +item.getChamp1()+ " | "+item.getData1Type()+" | "+item.getDocumentation1());

		
		item.setChamp2(fs.readString("champ2"));
		item.setData2Type(fs.readString("data2Type"));
		item.setDocumentation2(fs.readString("documentation2"));
		System.out.println("2:idx: > "+idx+"  " +item.getChamp2()+ " | "+item.getData2Type()+" | "+item.getDocumentation2());

		System.out.println(item.toString());

		return item;
	}

}
