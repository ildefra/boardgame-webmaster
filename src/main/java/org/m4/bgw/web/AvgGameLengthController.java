package org.m4.bgw.web;
import org.m4.bgw.domain.AvgGameLength;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/avggamelengths")
@Controller
@RooWebScaffold(path = "avggamelengths", formBackingObject = AvgGameLength.class)
public class AvgGameLengthController {
}
