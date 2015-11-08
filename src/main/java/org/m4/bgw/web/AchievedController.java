package org.m4.bgw.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.m4.bgw.domain.Achieved;
import org.m4.bgw.domain.AchievedRepository;
import org.m4.bgw.domain.AchievementRepository;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

	@Autowired
    AchievedRepository achievedRepository;

	@Autowired
    AchievementRepository achievementRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    PlayerRepository playerRepository;


	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid Achieved achieved, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achieved);
            return "achieveds/create";
        }
        uiModel.asMap().clear();
        achievedRepository.save(achieved);
        return "redirect:/achieveds/"
                + encodeId(achieved.getAchievedId(), httpServletRequest.getCharacterEncoding());
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Achieved());
        return "achieveds/create";
    }

	@RequestMapping(value = "/{achievedId}", produces = "text/html")
    public String show(@PathVariable("achievedId") Integer achievedId, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("achieved", achievedRepository.findOne(achievedId));
        uiModel.addAttribute("itemId", achievedId);
        return "achieveds/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sortFieldName", required = false) String sortFieldName,
            @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
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
    public String update(
            @Valid Achieved achieved, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achieved);
            return "achieveds/update";
        }
        uiModel.asMap().clear();
        achievedRepository.save(achieved);
        return "redirect:/achieveds/"
                + encodeId(achieved.getAchievedId(), httpServletRequest.getCharacterEncoding());
    }

	@RequestMapping(value = "/{achievedId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("achievedId") Integer achievedId, Model uiModel) {
        populateEditForm(uiModel, achievedRepository.findOne(achievedId));
        return "achieveds/update";
    }

	@RequestMapping(value = "/{achievedId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("achievedId") Integer achievedId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Achieved achieved = achievedRepository.findOne(achievedId);
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

	
    private String encodeId(Integer id, String encoding) {
        String enc = encoding != null ? encoding : WebUtils.DEFAULT_CHARACTER_ENCODING;
        try {
            return UriUtils.encodePathSegment(String.valueOf(id), enc);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
