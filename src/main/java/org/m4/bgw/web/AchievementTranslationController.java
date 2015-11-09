package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.AchievementRepository;
import org.m4.bgw.domain.AchievementTranslation;
import org.m4.bgw.domain.AchievementTranslationPK;
import org.m4.bgw.domain.AchievementTranslationRepository;
import org.m4.bgw.domain.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/achievementtranslations")
@Controller
public class AchievementTranslationController {

	private ConversionService conversionService;

	@Autowired
    AchievementTranslationRepository achievementTranslationRepository;

	@Autowired
    AchievementRepository achievementRepository;

	@Autowired
    LanguageRepository languageRepository;

	@Autowired
    public AchievementTranslationController(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid AchievementTranslation achievementTranslation,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achievementTranslation);
            return "achievementtranslations/create";
        }
        achievementTranslationRepository.save(achievementTranslation);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/achievementtranslations";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new AchievementTranslation());
        return "achievementtranslations/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") AchievementTranslationPK id, Model uiModel) {
        uiModel.addAttribute("achievementtranslation", achievementTranslationRepository.findOne(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "achievementtranslations/show";
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
            uiModel.addAttribute(
                    "achievementtranslations",
                    achievementTranslationRepository.findAll(new PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) achievementTranslationRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("achievementtranslations", achievementTranslationRepository.findAll());
        }
        return "achievementtranslations/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(
            @Valid AchievementTranslation achievementTranslation,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achievementTranslation);
            return "achievementtranslations/update";
        }
        achievementTranslationRepository.save(achievementTranslation);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/achievementtranslations";
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") AchievementTranslationPK id, Model uiModel) {
        populateEditForm(uiModel, achievementTranslationRepository.findOne(id));
        return "achievementtranslations/update";
    }

	@RequestMapping(
	        value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("id") AchievementTranslationPK id,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        AchievementTranslation achievementTranslation = achievementTranslationRepository.findOne(id);
        achievementTranslationRepository.delete(achievementTranslation);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/achievementtranslations";
    }

	void populateEditForm(Model uiModel, AchievementTranslation achievementTranslation) {
        uiModel.addAttribute("achievementTranslation", achievementTranslation);
        uiModel.addAttribute("achievements", achievementRepository.findAll());
        uiModel.addAttribute("languages", languageRepository.findAll());
    }
}
