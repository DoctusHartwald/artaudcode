package com.objis.springmvcdemo.controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.objis.springmvcdemo.domaine.Employe;
import com.objis.springmvcdemo.service.IEmployeManager;

@Controller
public class HomePageController {
	/*
	 * INJECTION Spring d'un bean Service dans le controleur. 
	 * Le bean Service est configuré dans objisapp-service.xml
	 */
	private IEmployeManager employeManager;

	@Autowired
	public void setEmployeManager(IEmployeManager employeManager) {
		this.employeManager = employeManager;
	}
	
	/*@RequestMapping("/home")
	protected ModelAndView retourControleur(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 // Lancement du Service et récupération données en base
		 
		List<Employe> listeEmployes = employeManager.getAllEmployes();

		
		 //Envoi Vue + Modèle MVC pour Affichage données vue
		 
		return new ModelAndView("home", "employes", listeEmployes);
	}*/
	
	@RequestMapping("/home")
	public void home(Model model){
		//Lancement du service et recuperation des données de la base de données 
		List<Employe> listeEmployes = employeManager.getAllEmployes();
		model.addAttribute("employes", listeEmployes);
	}
	
	@RequestMapping("/list")
	public void list(Model model){
		List<Employe> listeEmployes = employeManager.getAllEmployes();
		model.addAttribute("employes",  listeEmployes);
	}
	
}
