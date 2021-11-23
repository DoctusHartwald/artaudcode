package com.springsource.pizzashop.web;

import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.springsource.pizzashop.domain.Base;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "base", automaticallyMaintainView = true, formBackingObject = Base.class)
@RequestMapping("/base/**")
@Controller
public class BaseController {

	@RequestMapping(value = "/base", method = RequestMethod.POST)
    public String create(@Valid Base base, BindingResult result, ModelMap modelMap) {
        if (base == null) throw new IllegalArgumentException("A base is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("base", base);
            return "base/create";
        }
        base.persist();
        return "redirect:/base/" + base.getId();
    }

	@RequestMapping(value = "/base/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        modelMap.addAttribute("base", new Base());
        return "base/create";
    }

	@RequestMapping(value = "/base/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("base", Base.findBase(id));
        return "base/show";
    }

	@RequestMapping(value = "/base", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("bases", Base.findBaseEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Base.countBases() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("bases", Base.findAllBases());
        }
        return "base/list";
    }

	@RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Base base, BindingResult result, ModelMap modelMap) {
        if (base == null) throw new IllegalArgumentException("A base is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("base", base);
            return "base/update";
        }
        base.merge();
        return "redirect:/base/" + base.getId();
    }

	@RequestMapping(value = "/base/{id}/form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("base", Base.findBase(id));
        return "base/update";
    }

	@RequestMapping(value = "/base/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        Base.findBase(id).remove();
        return "redirect:/base?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
}
