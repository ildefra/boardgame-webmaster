package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.GameTagRepository;
import org.m4.bgw.domain.LanguageRepository;
import org.m4.bgw.domain.translate.TagTranslation;
import org.m4.bgw.domain.translate.TagTranslationRepository;
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


@RequestMapping("/tagtranslations")
@Controller
public class TagTranslationController {

	private ConversionService conversionService;

	@Autowired
    TagTranslationRepository tagTranslationRepository;

	@Autowired
    GameTagRepository gameTagRepository;

	@Autowired
    LanguageRepository languageRepository;

	@Autowired
    public TagTranslationController(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid TagTranslation tagTranslation,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tagTranslation);
            return "tagtranslations/create";
        }
        tagTranslationRepository.save(tagTranslation);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/tagtranslations";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new TagTranslation());
        return "tagtranslations/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("tagtranslation", tagTranslationRepository.findOne(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "tagtranslations/show";
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
                    "tagtranslations",
                    tagTranslationRepository.findAll(new PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) tagTranslationRepository.count() / sizeNo;
            uiModel.addAttribute(
                    "maxPages",
                    (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tagtranslations", tagTranslationRepository.findAll());
        }
        return "tagtranslations/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(
            @Valid TagTranslation tagTranslation,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tagTranslation);
            return "tagtranslations/update";
        }
        tagTranslationRepository.save(tagTranslation);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/tagtranslations";
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, tagTranslationRepository.findOne(id));
        return "tagtranslations/update";
    }

	@RequestMapping(
	        value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("id") Integer id,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        TagTranslation tagTranslation = tagTranslationRepository.findOne(id);
        tagTranslationRepository.delete(tagTranslation);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tagtranslations";
    }

	void populateEditForm(Model uiModel, TagTranslation tagTranslation) {
        uiModel.addAttribute("tagTranslation", tagTranslation);
        uiModel.addAttribute("gametags", gameTagRepository.findAll());
        uiModel.addAttribute("languages", languageRepository.findAll());
    }
}
