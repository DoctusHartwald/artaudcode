package com.myshop.controller.plate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myshop.controller.account.ResourceNotFoundException;
import com.myshop.service.plate.IPlateService;

@Controller
@RequestMapping(value="/plat")
public class PlatController {
	
	private Map<Integer, Plat> plats = new ConcurrentHashMap<Integer, Plat>();
	
	@Autowired
	@Qualifier("platService")
	private IPlateService mPlatService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(new Plat());
		return "plat/createForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create(@Valid com.myshop.domain.plate.Plat plat, BindingResult result) {
		if (result.hasErrors()) {
			return "plat/createForm";
		}
		
		
		//this.plats.put(plat.assignId(), plat);
		System.out.println("==========================     "+plat.toString());
		mPlatService.persist(plat);
		return "plat/confirmation";
	}
	
	
	
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String getView(@PathVariable Long id, Model model) {
		Plat plat = this.plats.get(id);
		if (plat == null) {
			throw new ResourceNotFoundException(id);
		}
		model.addAttribute(plat);
		return "plat/view";
	}
	
}
