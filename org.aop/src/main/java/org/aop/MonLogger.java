package org.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.springframework.stereotype.Service;

@Service
public class MonLogger {
	private final static Logger LOG = Logger.getLogger(MonLogger.class);
	
  // Cette méthode est appelée à chaque fois (et avant) qu'une méthode du package ew.service est interceptée 
  public void logMethodEntry(JoinPoint joinPoint) {
    
    Object[] args = joinPoint.getArgs();

    // Nom de la méthode interceptée
    String name = joinPoint.getSignature().toLongString();
    StringBuffer sb = new StringBuffer(name + " called with: [");

    // Liste des valeurs des arguments reçus par la méthode
    for(int i = 0; i < args.length; i++) {
      Object o = args[i];
      sb.append("'"+o+"'");
      sb.append((i == args.length - 1) ? "" : ", ");
    }
    sb.append("]");
    
    System.out.println(sb);
    LOG.info(sb.toString());
  }

  // Cette méthode est appelée à chaque fois (et après) qu'une méthode du package ew.service est interceptée 
  // Elle reçoit en argument 'result' qui est le retour de la méthode interceptée
  public void logMethodExit(StaticPart staticPart, Object result) {
  
    // Nom de la méthode interceptée
    String name = staticPart.getSignature().toLongString();
  
    System.out.println(name + " returning: [" + result + "]");
    LOG.info(name + " returning: [" + result + "]");
  }
  
}
