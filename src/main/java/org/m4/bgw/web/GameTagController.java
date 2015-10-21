package org.m4.bgw.web;
import org.m4.bgw.domain.GameTag;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gametags")
@Controller
@RooWebScaffold(path = "gametags", formBackingObject = GameTag.class)
public class GameTagController {
}
