package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.AchievedRepository;
import org.m4.bgw.domain.Achievement;
import org.m4.bgw.domain.AchievementRepository;
import org.m4.bgw.domain.AchievementTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/achievements")
@Controller
public class AchievementController {

	@Autowired
    AchievementRepository achievementRepository;

	@Autowired
    AchievedRepository achievedRepository;

	@Autowired
    AchievementTranslationRepository achievementTranslationRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid Achievement achievement,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achievement);
            return "achievements/create";
        }
        achievementRepository.save(achievement);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/achievements";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Achievement());
        return "achievements/create";
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
            uiModel.addAttribute("achievements", achievementRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) achievementRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("achievements", achievementRepository.findAll());
        }
        return "achievements/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(
            @Valid Achievement achievement,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achievement);
            return "achievements/update";
        }
        achievementRepository.save(achievement);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/achievements";
    }

	@RequestMapping(value = "/{achievementId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("achievementId") Short achievementId, Model uiModel) {
        populateEditForm(uiModel, achievementRepository.findOne(achievementId));
        return "achievements/update";
    }

	@RequestMapping(value = "/{achievementId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("achievementId") Short achievementId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Achievement achievement = achievementRepository.findOne(achievementId);
        achievementRepository.delete(achievement);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/achievements";
    }

	void populateEditForm(Model uiModel, Achievement achievement) {
        uiModel.addAttribute("achievement", achievement);
        uiModel.addAttribute("achieveds", achievedRepository.findAll());
        uiModel.addAttribute("achievementtranslations", achievementTranslationRepository.findAll());
    }
}
