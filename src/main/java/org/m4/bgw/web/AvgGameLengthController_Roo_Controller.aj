// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.Boardgame;
import org.m4.bgw.domain.AvgGameLength;
import org.m4.bgw.domain.AvgGameLengthPK;
import org.m4.bgw.domain.TimeLimit;
import org.m4.bgw.web.AvgGameLengthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect AvgGameLengthController_Roo_Controller {
    
    private ConversionService AvgGameLengthController.conversionService;
    
    @Autowired
    public AvgGameLengthController.new(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String AvgGameLengthController.create(@Valid AvgGameLength avgGameLength, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, avgGameLength);
            return "avggamelengths/create";
        }
        uiModel.asMap().clear();
        avgGameLength.persist();
        return "redirect:/avggamelengths/" + encodeUrlPathSegment(conversionService.convert(avgGameLength.getId(), String.class), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String AvgGameLengthController.createForm(Model uiModel) {
        populateEditForm(uiModel, new AvgGameLength());
        return "avggamelengths/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String AvgGameLengthController.show(@PathVariable("id") AvgGameLengthPK id, Model uiModel) {
        uiModel.addAttribute("avggamelength", AvgGameLength.findAvgGameLength(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "avggamelengths/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String AvgGameLengthController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("avggamelengths", AvgGameLength.findAvgGameLengthEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) AvgGameLength.countAvgGameLengths() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("avggamelengths", AvgGameLength.findAllAvgGameLengths(sortFieldName, sortOrder));
        }
        return "avggamelengths/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String AvgGameLengthController.update(@Valid AvgGameLength avgGameLength, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, avgGameLength);
            return "avggamelengths/update";
        }
        uiModel.asMap().clear();
        avgGameLength.merge();
        return "redirect:/avggamelengths/" + encodeUrlPathSegment(conversionService.convert(avgGameLength.getId(), String.class), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String AvgGameLengthController.updateForm(@PathVariable("id") AvgGameLengthPK id, Model uiModel) {
        populateEditForm(uiModel, AvgGameLength.findAvgGameLength(id));
        return "avggamelengths/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String AvgGameLengthController.delete(@PathVariable("id") AvgGameLengthPK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        AvgGameLength avgGameLength = AvgGameLength.findAvgGameLength(id);
        avgGameLength.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/avggamelengths";
    }
    
    void AvgGameLengthController.populateEditForm(Model uiModel, AvgGameLength avgGameLength) {
        uiModel.addAttribute("avgGameLength", avgGameLength);
        uiModel.addAttribute("boardgames", Boardgame.findAllBoardgames());
        uiModel.addAttribute("timelimits", TimeLimit.findAllTimeLimits());
    }
    
    String AvgGameLengthController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
