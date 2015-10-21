// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.GameTag;
import org.m4.bgw.domain.GameTagRepository;
import org.m4.bgw.domain.TagTranslationRepository;
import org.m4.bgw.web.GameTagController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect GameTagController_Roo_Controller {
    
    @Autowired
    GameTagRepository GameTagController.gameTagRepository;
    
    @Autowired
    BoardgameRepository GameTagController.boardgameRepository;
    
    @Autowired
    TagTranslationRepository GameTagController.tagTranslationRepository;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String GameTagController.create(@Valid GameTag gameTag, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameTag);
            return "gametags/create";
        }
        uiModel.asMap().clear();
        gameTagRepository.save(gameTag);
        return "redirect:/gametags/" + encodeUrlPathSegment(gameTag.getName().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String GameTagController.createForm(Model uiModel) {
        populateEditForm(uiModel, new GameTag());
        return "gametags/create";
    }
    
    @RequestMapping(value = "/{name}", produces = "text/html")
    public String GameTagController.show(@PathVariable("name") String name, Model uiModel) {
        uiModel.addAttribute("gametag", gameTagRepository.findOne(name));
        uiModel.addAttribute("itemId", name);
        return "gametags/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String GameTagController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("gametags", gameTagRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) gameTagRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("gametags", gameTagRepository.findAll());
        }
        return "gametags/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String GameTagController.update(@Valid GameTag gameTag, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameTag);
            return "gametags/update";
        }
        uiModel.asMap().clear();
        gameTagRepository.save(gameTag);
        return "redirect:/gametags/" + encodeUrlPathSegment(gameTag.getName().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{name}", params = "form", produces = "text/html")
    public String GameTagController.updateForm(@PathVariable("name") String name, Model uiModel) {
        populateEditForm(uiModel, gameTagRepository.findOne(name));
        return "gametags/update";
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE, produces = "text/html")
    public String GameTagController.delete(@PathVariable("name") String name, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        GameTag gameTag = gameTagRepository.findOne(name);
        gameTagRepository.delete(gameTag);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/gametags";
    }
    
    void GameTagController.populateEditForm(Model uiModel, GameTag gameTag) {
        uiModel.addAttribute("gameTag", gameTag);
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        uiModel.addAttribute("tagtranslations", tagTranslationRepository.findAll());
    }
    
    String GameTagController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
