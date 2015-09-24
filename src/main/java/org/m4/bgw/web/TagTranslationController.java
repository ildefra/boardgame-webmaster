package org.m4.bgw.web;
import org.m4.bgw.domain.TagTranslation;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tagtranslations")
@Controller
@RooWebScaffold(path = "tagtranslations", formBackingObject = TagTranslation.class)
public class TagTranslationController {
}
