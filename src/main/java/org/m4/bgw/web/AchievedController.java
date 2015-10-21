package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.m4.bgw.domain.Achieved;
import org.m4.bgw.domain.AchievedPK;
import org.m4.bgw.domain.AchievedRepository;
import org.m4.bgw.domain.AchievementRepository;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

@RequestMapping("/achieveds")
@Controller
public class AchievedController {

	private ConversionService conversionService;

	@Autowired
    AchievedRepository achievedRepository;

	@Autowired
    AchievementRepository achievementRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    PlayerRepository playerRepository;

	@Autowired
    public AchievedController(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Achieved achieved, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achieved);
            return "achieveds/create";
        }
        uiModel.asMap().clear();
        achievedRepository.save(achieved);
        return "redirect:/achieveds/" + encodeUrlPathSegment(conversionService.convert(achieved.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Achieved());
        return "achieveds/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") AchievedPK id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("achieved", achievedRepository.findOne(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "achieveds/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("achieveds", achievedRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) achievedRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("achieveds", achievedRepository.findAll());
        }
        addDateTimeFormatPatterns(uiModel);
        return "achieveds/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Achieved achieved, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achieved);
            return "achieveds/update";
        }
        uiModel.asMap().clear();
        achievedRepository.save(achieved);
        return "redirect:/achieveds/" + encodeUrlPathSegment(conversionService.convert(achieved.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") AchievedPK id, Model uiModel) {
        populateEditForm(uiModel, achievedRepository.findOne(id));
        return "achieveds/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") AchievedPK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Achieved achieved = achievedRepository.findOne(id);
        achievedRepository.delete(achieved);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/achieveds";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("achieved_ondate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Achieved achieved) {
        uiModel.addAttribute("achieved", achieved);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("achievements", achievementRepository.findAll());
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        uiModel.addAttribute("players", playerRepository.findAll());
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
