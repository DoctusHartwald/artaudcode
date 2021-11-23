package com.springsource.pizzashop.web;

import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.springsource.pizzashop.domain.Pizza;
import com.springsource.pizzashop.domain.PizzaOrder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "pizzaorder", automaticallyMaintainView = true, formBackingObject = PizzaOrder.class)
@RequestMapping("/pizzaorder/**")
@Controller
public class PizzaOrderController {

	@RequestMapping(value = "/pizzaorder", method = RequestMethod.POST)
    public String create(@Valid PizzaOrder pizzaOrder, BindingResult result, ModelMap modelMap) {
        if (pizzaOrder == null) throw new IllegalArgumentException("A pizzaOrder is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("pizzaOrder", pizzaOrder);
            modelMap.addAttribute("pizzas", Pizza.findAllPizzas());
            modelMap.addAttribute("pizzaOrder_deliveryDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
            return "pizzaorder/create";
        }
        pizzaOrder.persist();
        return "redirect:/pizzaorder/" + pizzaOrder.getId();
    }

	@RequestMapping(value = "/pizzaorder/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        modelMap.addAttribute("pizzaOrder", new PizzaOrder());
        modelMap.addAttribute("pizzas", Pizza.findAllPizzas());
        modelMap.addAttribute("pizzaOrder_deliveryDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "pizzaorder/create";
    }

	@RequestMapping(value = "/pizzaorder/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("pizzaOrder_deliveryDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        modelMap.addAttribute("pizzaOrder", PizzaOrder.findPizzaOrder(id));
        return "pizzaorder/show";
    }

	@RequestMapping(value = "/pizzaorder", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("pizzaorders", PizzaOrder.findPizzaOrderEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) PizzaOrder.countPizzaOrders() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("pizzaorders", PizzaOrder.findAllPizzaOrders());
        }
        modelMap.addAttribute("pizzaOrder_deliveryDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "pizzaorder/list";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid PizzaOrder pizzaOrder, BindingResult result, ModelMap modelMap) {
        if (pizzaOrder == null) throw new IllegalArgumentException("A pizzaOrder is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("pizzaOrder", pizzaOrder);
            modelMap.addAttribute("pizzas", Pizza.findAllPizzas());
            modelMap.addAttribute("pizzaOrder_deliveryDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
            return "pizzaorder/update";
        }
        pizzaOrder.merge();
        return "redirect:/pizzaorder/" + pizzaOrder.getId();
    }

	@RequestMapping(value = "/pizzaorder/{id}/form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("pizzaOrder", PizzaOrder.findPizzaOrder(id));
        modelMap.addAttribute("pizzas", Pizza.findAllPizzas());
        modelMap.addAttribute("pizzaOrder_deliveryDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "pizzaorder/update";
    }

	@RequestMapping(value = "/pizzaorder/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        PizzaOrder.findPizzaOrder(id).remove();
        return "redirect:/pizzaorder?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
}
