package org.m4.bgw.web;
import org.m4.bgw.domain.LevelTranslation;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/leveltranslations")
@Controller
@RooWebScaffold(path = "leveltranslations", formBackingObject = LevelTranslation.class)
public class LevelTranslationController {
}
