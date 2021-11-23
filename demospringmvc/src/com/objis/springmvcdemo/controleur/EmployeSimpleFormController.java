package com.objis.springmvcdemo.controleur;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.objis.springmvcdemo.domaine.Employe;
import com.objis.springmvcdemo.service.EmployeManager;

public class EmployeSimpleFormController extends SimpleFormController {

	// INJECTION d'un Service dans le controleur. 
	private EmployeManager employeManager;

	public void setEmployeManager(EmployeManager employeManager) {
		this.employeManager = employeManager;
	}

	public EmployeSimpleFormController() {
		//Initialise Classe (Employe.class) 
		setCommandClass(Employe.class);
		setCommandName("employe");
	}

	protected ModelAndView onSubmit(Object command,BindException errors) throws Exception {

		Employe employe = (Employe)command;

		//Récupération database d'un Employé dont le login correspond au champ 'login' du formulaire		
		//Employe newemploye = employeManager.getEmploye(employe.getLogin());
		Employe newemploye = employeManager.getEmploye(employe.getLogin());

		// TODO : gérer absence d'employé avec login spécifié !!

		// TODO : gérer comparaison mots de passe !!!

		// Renvoie sur la vue de l'employé
		Map model = errors.getModel();
		model.put("employe", newemploye);
		return new ModelAndView("accueil",model);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		Employe employe = (Employe) super.formBackingObject(request);
		return employe;
	}
}
