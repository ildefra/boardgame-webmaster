package org.m4.bgw.web;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.m4.bgw.domain.AchievedRepository;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.CountryRepository;
import org.m4.bgw.domain.GenderRepository;
import org.m4.bgw.domain.LanguageRepository;
import org.m4.bgw.domain.PlayedRepository;
import org.m4.bgw.domain.Player;
import org.m4.bgw.domain.PlayerRepository;
import org.m4.bgw.domain.UserLevelRepository;
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

@RequestMapping("/players")
@Controller
public class PlayerController {

	@Autowired
    PlayerRepository playerRepository;

	@Autowired
    AchievedRepository achievedRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    CountryRepository countryRepository;

	@Autowired
    GenderRepository genderRepository;

	@Autowired
    LanguageRepository languageRepository;

	@Autowired
    PlayedRepository playedRepository;

	@Autowired
    UserLevelRepository userLevelRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Player player, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, player);
            return "players/create";
        }
        uiModel.asMap().clear();
        playerRepository.save(player);
        return "redirect:/players/" + encodeUrlPathSegment(player.getUsername().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Player());
        return "players/create";
    }

	@RequestMapping(value = "/{username}", produces = "text/html")
    public String show(@PathVariable("username") String username, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("player", playerRepository.findOne(username));
        uiModel.addAttribute("itemId", username);
        return "players/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("players", playerRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) playerRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("players", playerRepository.findAll());
        }
        addDateTimeFormatPatterns(uiModel);
        return "players/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Player player, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, player);
            return "players/update";
        }
        uiModel.asMap().clear();
        playerRepository.save(player);
        return "redirect:/players/" + encodeUrlPathSegment(player.getUsername().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{username}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("username") String username, Model uiModel) {
        populateEditForm(uiModel, playerRepository.findOne(username));
        return "players/update";
    }

	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("username") String username, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Player player = playerRepository.findOne(username);
        playerRepository.delete(player);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/players";
    }

	
	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute(
                "player_registrationdtm_date_format",
                DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute(
                "player_birthdate_date_format",
                DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	
	void populateEditForm(Model uiModel, Player player) {
        uiModel.addAttribute("player", player);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("achieveds", achievedRepository.findAll());
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        uiModel.addAttribute("countrys", countryRepository.findAll());
        uiModel.addAttribute("genders", genderRepository.findAll());
        uiModel.addAttribute("languages", languageRepository.findAll());
        uiModel.addAttribute("playeds", playedRepository.findAll());
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
