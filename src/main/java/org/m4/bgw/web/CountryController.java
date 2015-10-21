package org.m4.bgw.web;
import org.m4.bgw.domain.Country;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/countrys")
@Controller
@RooWebScaffold(path = "countrys", formBackingObject = Country.class, create = false, update = false, delete = false)
public class CountryController {
}
