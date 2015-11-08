package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.LevelTranslationRepository;
import org.m4.bgw.domain.PlayerRepository;
import org.m4.bgw.domain.UserLevel;
import org.m4.bgw.domain.UserLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/userlevels")
@Controller
public class UserLevelController {

	@Autowired
    UserLevelRepository userLevelRepository;

	@Autowired
    LevelTranslationRepository levelTranslationRepository;

	@Autowired
    PlayerRepository playerRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid UserLevel userLevel,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userLevel);
            return "userlevels/create";
        }
        userLevelRepository.save(userLevel);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/userlevels";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new UserLevel());
        return "userlevels/create";
    }


	@RequestMapping(produces = "text/html")
    public String list(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sortFieldName", required = false) String sortFieldName,
            @RequestParam(value = "sortOrder", required = false) String sortOrder,
            Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("userlevels", userLevelRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) userLevelRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("userlevels", userLevelRepository.findAll());
        }
        return "userlevels/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(
            @Valid UserLevel userLevel,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userLevel);
            return "userlevels/update";
        }
        userLevelRepository.save(userLevel);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/userlevels";
    }

	@RequestMapping(value = "/{levelId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("levelId") Short levelId, Model uiModel) {
        populateEditForm(uiModel, userLevelRepository.findOne(levelId));
        return "userlevels/update";
    }

	@RequestMapping(value = "/{levelId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("levelId") Short levelId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        UserLevel userLevel = userLevelRepository.findOne(levelId);
        userLevelRepository.delete(userLevel);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/userlevels";
    }

	void populateEditForm(Model uiModel, UserLevel userLevel) {
        uiModel.addAttribute("userLevel", userLevel);
        uiModel.addAttribute("leveltranslations", levelTranslationRepository.findAll());
        uiModel.addAttribute("players", playerRepository.findAll());
    }
}
