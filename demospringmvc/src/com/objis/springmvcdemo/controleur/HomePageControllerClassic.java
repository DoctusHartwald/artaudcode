package com.objis.springmvcdemo.controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.objis.springmvcdemo.domaine.Employe;
import com.objis.springmvcdemo.service.EmployeManager;

public class HomePageControllerClassic extends AbstractController {
	/*
	 * INJECTION Spring d'un bean Service dans le controleur. 
	 * Le bean Service est configuré dans objisapp-service.xml
	 */
	private EmployeManager employeManager;

	public void setEmployeManager(EmployeManager employeManager) {
		this.employeManager = employeManager;
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * Lancement du Service et récupération données en base
		 */
		List<Employe> listeEmployes = employeManager.getAllEmployes();

		/*
		 * Envoi Vue + Modèle MVC pour Affichage données vue
		 */
		return new ModelAndView("home", "employes", listeEmployes);
		//home nom logique de la vue a  afficher 
		//employee : nom de objet du modele MVC à transmettre à la vue 
		//listeEmployes : valeur de objet modele à transmettre à la vue .
	}
}
