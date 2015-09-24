package org.m4.bgw.web;
import org.m4.bgw.domain.TimeLimit;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timelimits")
@Controller
@RooWebScaffold(path = "timelimits", formBackingObject = TimeLimit.class)
public class TimeLimitController {
}
