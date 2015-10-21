package org.m4.bgw.web;
import org.m4.bgw.domain.Achieved;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/achieveds")
@Controller
@RooWebScaffold(path = "achieveds", formBackingObject = Achieved.class)
public class AchievedController {
}
