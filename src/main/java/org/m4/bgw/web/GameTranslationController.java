package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.GameTranslation;
import org.m4.bgw.domain.GameTranslationPK;
import org.m4.bgw.domain.GameTranslationRepository;
import org.m4.bgw.domain.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/gametranslations")
@Controller
public class GameTranslationController {

	private ConversionService conversionService;

	@Autowired
    GameTranslationRepository gameTranslationRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    LanguageRepository languageRepository;

	@Autowired
    public GameTranslationController(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid GameTranslation gameTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameTranslation);
            return "gametranslations/create";
        }
        uiModel.asMap().clear();
        gameTranslationRepository.save(gameTranslation);
        return "redirect:/gametranslations/" + encodeUrlPathSegment(conversionService.convert(gameTranslation.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new GameTranslation());
        return "gametranslations/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") GameTranslationPK id, Model uiModel) {
        uiModel.addAttribute("gametranslation", gameTranslationRepository.findOne(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "gametranslations/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("gametranslations", gameTranslationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) gameTranslationRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("gametranslations", gameTranslationRepository.findAll());
        }
        return "gametranslations/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid GameTranslation gameTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameTranslation);
            return "gametranslations/update";
        }
        uiModel.asMap().clear();
        gameTranslationRepository.save(gameTranslation);
        return "redirect:/gametranslations/" + encodeUrlPathSegment(conversionService.convert(gameTranslation.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") GameTranslationPK id, Model uiModel) {
        populateEditForm(uiModel, gameTranslationRepository.findOne(id));
        return "gametranslations/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") GameTranslationPK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        GameTranslation gameTranslation = gameTranslationRepository.findOne(id);
        gameTranslationRepository.delete(gameTranslation);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/gametranslations";
    }

	void populateEditForm(Model uiModel, GameTranslation gameTranslation) {
        uiModel.addAttribute("gameTranslation", gameTranslation);
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        uiModel.addAttribute("languages", languageRepository.findAll());
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
