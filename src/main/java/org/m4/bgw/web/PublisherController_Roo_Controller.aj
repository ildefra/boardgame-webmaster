// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.Publisher;
import org.m4.bgw.domain.PublisherRepository;
import org.m4.bgw.web.PublisherController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect PublisherController_Roo_Controller {
    
    @Autowired
    PublisherRepository PublisherController.publisherRepository;
    
    @Autowired
    BoardgameRepository PublisherController.boardgameRepository;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PublisherController.create(@Valid Publisher publisher, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, publisher);
            return "publishers/create";
        }
        uiModel.asMap().clear();
        publisherRepository.save(publisher);
        return "redirect:/publishers/" + encodeUrlPathSegment(publisher.getName().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PublisherController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Publisher());
        return "publishers/create";
    }
    
    @RequestMapping(value = "/{name}", produces = "text/html")
    public String PublisherController.show(@PathVariable("name") String name, Model uiModel) {
        uiModel.addAttribute("publisher", publisherRepository.findOne(name));
        uiModel.addAttribute("itemId", name);
        return "publishers/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PublisherController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("publishers", publisherRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) publisherRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("publishers", publisherRepository.findAll());
        }
        return "publishers/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PublisherController.update(@Valid Publisher publisher, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, publisher);
            return "publishers/update";
        }
        uiModel.asMap().clear();
        publisherRepository.save(publisher);
        return "redirect:/publishers/" + encodeUrlPathSegment(publisher.getName().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{name}", params = "form", produces = "text/html")
    public String PublisherController.updateForm(@PathVariable("name") String name, Model uiModel) {
        populateEditForm(uiModel, publisherRepository.findOne(name));
        return "publishers/update";
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE, produces = "text/html")
    public String PublisherController.delete(@PathVariable("name") String name, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Publisher publisher = publisherRepository.findOne(name);
        publisherRepository.delete(publisher);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/publishers";
    }
    
    void PublisherController.populateEditForm(Model uiModel, Publisher publisher) {
        uiModel.addAttribute("publisher", publisher);
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
    }
    
    String PublisherController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
