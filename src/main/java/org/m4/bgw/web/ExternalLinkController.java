package org.m4.bgw.web;
import org.m4.bgw.domain.ExternalLink;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/externallinks")
@Controller
@RooWebScaffold(path = "externallinks", formBackingObject = ExternalLink.class)
public class ExternalLinkController {
}
