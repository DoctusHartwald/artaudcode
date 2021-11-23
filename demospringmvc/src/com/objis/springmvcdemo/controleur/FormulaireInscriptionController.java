
package com.objis.springmvcdemo.controleur;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.objis.springmvcdemo.domaine.Employe;

@Controller
public class FormulaireInscriptionController {
	private static final Logger log = Logger.getLogger(FormulaireInscriptionController.class);
	
	private String merciViewName;
	
	public void setThanksViewName(String merciViewName){
		this.merciViewName=merciViewName; 
	}
	
	@RequestMapping(method= RequestMethod.GET,value="/enregistrement")
	public void form(Model model){
		model.addAttribute("employe",new Employe());
		
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public String enregistrementDonneesFormulaire(Employe employe){
		
		log.info("Enregistrement employe "+ employe );
		
		return "merci";
	}
		
}
