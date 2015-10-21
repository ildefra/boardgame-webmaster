package org.m4.bgw.web;
import org.m4.bgw.domain.Language;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/languages")
@Controller
@RooWebScaffold(path = "languages", formBackingObject = Language.class, create = false, update = false, delete = false)
public class LanguageController {
}
