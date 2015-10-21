package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.LanguageRepository;
import org.m4.bgw.domain.LevelTranslation;
import org.m4.bgw.domain.LevelTranslationPK;
import org.m4.bgw.domain.LevelTranslationRepository;
import org.m4.bgw.domain.UserLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/leveltranslations")
@Controller
@RooWebScaffold(path = "leveltranslations", formBackingObject = LevelTranslation.class)
public class LevelTranslationController {

	private ConversionService conversionService;

	@Autowired
    LevelTranslationRepository levelTranslationRepository;

	@Autowired
    LanguageRepository languageRepository;

	@Autowired
    UserLevelRepository userLevelRepository;

	@Autowired
    public LevelTranslationController(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid LevelTranslation levelTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, levelTranslation);
            return "leveltranslations/create";
        }
        uiModel.asMap().clear();
        levelTranslationRepository.save(levelTranslation);
        return "redirect:/leveltranslations/" + encodeUrlPathSegment(conversionService.convert(levelTranslation.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new LevelTranslation());
        return "leveltranslations/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") LevelTranslationPK id, Model uiModel) {
        uiModel.addAttribute("leveltranslation", levelTranslationRepository.findOne(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "leveltranslations/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("leveltranslations", levelTranslationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) levelTranslationRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("leveltranslations", levelTranslationRepository.findAll());
        }
        return "leveltranslations/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid LevelTranslation levelTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, levelTranslation);
            return "leveltranslations/update";
        }
        uiModel.asMap().clear();
        levelTranslationRepository.save(levelTranslation);
        return "redirect:/leveltranslations/" + encodeUrlPathSegment(conversionService.convert(levelTranslation.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") LevelTranslationPK id, Model uiModel) {
        populateEditForm(uiModel, levelTranslationRepository.findOne(id));
        return "leveltranslations/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") LevelTranslationPK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        LevelTranslation levelTranslation = levelTranslationRepository.findOne(id);
        levelTranslationRepository.delete(levelTranslation);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/leveltranslations";
    }

	void populateEditForm(Model uiModel, LevelTranslation levelTranslation) {
        uiModel.addAttribute("levelTranslation", levelTranslation);
        uiModel.addAttribute("languages", languageRepository.findAll());
        uiModel.addAttribute("userlevels", userLevelRepository.findAll());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
