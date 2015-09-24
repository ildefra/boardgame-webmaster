package org.m4.bgw.web;
import org.m4.bgw.domain.GameDesigner;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gamedesigners")
@Controller
@RooWebScaffold(path = "gamedesigners", formBackingObject = GameDesigner.class)
public class GameDesignerController {
}
