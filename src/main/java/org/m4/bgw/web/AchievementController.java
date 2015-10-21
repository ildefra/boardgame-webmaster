package org.m4.bgw.web;
import org.m4.bgw.domain.Achievement;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/achievements")
@Controller
@RooWebScaffold(path = "achievements", formBackingObject = Achievement.class)
public class AchievementController {
}
