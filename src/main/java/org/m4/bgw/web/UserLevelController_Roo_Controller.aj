// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.LevelTranslation;
import org.m4.bgw.domain.Player;
import org.m4.bgw.domain.UserLevel;
import org.m4.bgw.web.UserLevelController;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect UserLevelController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UserLevelController.create(@Valid UserLevel userLevel, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userLevel);
            return "userlevels/create";
        }
        uiModel.asMap().clear();
        userLevel.persist();
        return "redirect:/userlevels/" + encodeUrlPathSegment(userLevel.getLevelId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UserLevelController.createForm(Model uiModel) {
        populateEditForm(uiModel, new UserLevel());
        return "userlevels/create";
    }
    
    @RequestMapping(value = "/{levelId}", produces = "text/html")
    public String UserLevelController.show(@PathVariable("levelId") Short levelId, Model uiModel) {
        uiModel.addAttribute("userlevel", UserLevel.findUserLevel(levelId));
        uiModel.addAttribute("itemId", levelId);
        return "userlevels/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String UserLevelController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("userlevels", UserLevel.findUserLevelEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) UserLevel.countUserLevels() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("userlevels", UserLevel.findAllUserLevels(sortFieldName, sortOrder));
        }
        return "userlevels/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String UserLevelController.update(@Valid UserLevel userLevel, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userLevel);
            return "userlevels/update";
        }
        uiModel.asMap().clear();
        userLevel.merge();
        return "redirect:/userlevels/" + encodeUrlPathSegment(userLevel.getLevelId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{levelId}", params = "form", produces = "text/html")
    public String UserLevelController.updateForm(@PathVariable("levelId") Short levelId, Model uiModel) {
        populateEditForm(uiModel, UserLevel.findUserLevel(levelId));
        return "userlevels/update";
    }
    
    @RequestMapping(value = "/{levelId}", method = RequestMethod.DELETE, produces = "text/html")
    public String UserLevelController.delete(@PathVariable("levelId") Short levelId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        UserLevel userLevel = UserLevel.findUserLevel(levelId);
        userLevel.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/userlevels";
    }
    
    void UserLevelController.populateEditForm(Model uiModel, UserLevel userLevel) {
        uiModel.addAttribute("userLevel", userLevel);
        uiModel.addAttribute("leveltranslations", LevelTranslation.findAllLevelTranslations());
        uiModel.addAttribute("players", Player.findAllPlayers());
    }
    
    String UserLevelController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
