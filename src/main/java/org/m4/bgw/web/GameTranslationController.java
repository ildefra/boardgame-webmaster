package org.m4.bgw.web;
import org.m4.bgw.domain.GameTranslation;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gametranslations")
@Controller
@RooWebScaffold(path = "gametranslations", formBackingObject = GameTranslation.class)
public class GameTranslationController {
}
