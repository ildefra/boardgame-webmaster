package org.m4.bgw.web;
import org.m4.bgw.domain.UserLevel;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userlevels")
@Controller
@RooWebScaffold(path = "userlevels", formBackingObject = UserLevel.class)
public class UserLevelController {
}
