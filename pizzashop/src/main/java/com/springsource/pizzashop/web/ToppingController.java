package com.springsource.pizzashop.web;

import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.springsource.pizzashop.domain.Topping;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "topping", automaticallyMaintainView = true, formBackingObject = Topping.class)
@RequestMapping("/topping/**")
@Controller
public class ToppingController {

	@RequestMapping(value = "/topping", method = RequestMethod.POST)
    public String create(@Valid Topping topping, BindingResult result, ModelMap modelMap) {
        if (topping == null) throw new IllegalArgumentException("A topping is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("topping", topping);
            return "topping/create";
        }
        topping.persist();
        return "redirect:/topping/" + topping.getId();
    }

	@RequestMapping(value = "/topping/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        modelMap.addAttribute("topping", new Topping());
        return "topping/create";
    }

	@RequestMapping(value = "/topping/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("topping", Topping.findTopping(id));
        return "topping/show";
    }

	@RequestMapping(value = "/topping", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("toppings", Topping.findToppingEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Topping.countToppings() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("toppings", Topping.findAllToppings());
        }
        return "topping/list";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Topping topping, BindingResult result, ModelMap modelMap) {
        if (topping == null) throw new IllegalArgumentException("A topping is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("topping", topping);
            return "topping/update";
        }
        topping.merge();
        return "redirect:/topping/" + topping.getId();
    }

	@RequestMapping(value = "/topping/{id}/form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("topping", Topping.findTopping(id));
        return "topping/update";
    }

	@RequestMapping(value = "/topping/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        Topping.findTopping(id).remove();
        return "redirect:/topping?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
}
