package org.m4.bgw.web;
import org.m4.bgw.domain.Publisher;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/publishers")
@Controller
@RooWebScaffold(path = "publishers", formBackingObject = Publisher.class)
public class PublisherController {
}
