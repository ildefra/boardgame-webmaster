package org.m4.bgw.web;
import org.m4.bgw.domain.AchievementTranslation;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/achievementtranslations")
@Controller
@RooWebScaffold(path = "achievementtranslations", formBackingObject = AchievementTranslation.class)
public class AchievementTranslationController {
}
