package com.objis.springmvcdemo.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.objis.springmvcdemo.domaine.Plat;
import com.objis.springmvcdemo.service.IPlatService;

@Controller
public class PlatFormController{

	
	@Autowired
	@Qualifier("platService")
	private IPlatService platService;


	@RequestMapping("/plat")
	public void list(Model model){
		System.out.println("Appel de listePlat");
		Plat lePlatJour = platService.findById(1);
		model.addAttribute("lePlatJour",  lePlatJour);

	}
	
	@RequestMapping(value="/nouveauplat")
	//public void form( Model modelMap){
	public void submitForm(@ModelAttribute("nouveauplat") Plat mplat){
		
		
		System.out.println("Desxription : "+mplat.getDescription());
		//modelMap.addAttribute("nouveauplat", new Plat());
		//System.out.println("=>> GET "+platParam.getDescription());
		//System.out.println("Entre dans le GET nouveau :" + platParam.getDescription()+"  "+platParam.getNomPlat());
		
		Plat vPlat = new Plat(mplat.getNomPlat(),mplat.getDescription(),mplat.getDescription());
		
		if(mplat.getDescription()!=null){
			platService.persist(vPlat);
			System.out.println("yeahhhhhhhhhhhhhhh");
		}
			
		
		//model.addAttribute("plat",new Plat());
		
		
	}

	@RequestMapping(method= RequestMethod.POST)
	public String enregistrementDonneesFormulaire(Plat plat){
		System.out.println("On est dans le POST !!");
		return "plat";
	}

}

