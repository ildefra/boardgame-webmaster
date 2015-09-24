package org.m4.bgw.web;
import org.m4.bgw.Boardgame;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/boardgames")
@Controller
@RooWebScaffold(path = "boardgames", formBackingObject = Boardgame.class)
public class BoardgameController {
}
