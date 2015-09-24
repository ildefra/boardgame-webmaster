package org.m4.bgw.web;
import org.m4.bgw.domain.Gender;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/genders")
@Controller
@RooWebScaffold(path = "genders", formBackingObject = Gender.class)
public class GenderController {
}
