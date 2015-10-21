package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.GameTable;
import org.m4.bgw.domain.GameTableRepository;
import org.m4.bgw.domain.PlayedRepository;
import org.m4.bgw.domain.TimeLimitRepository;
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

@RequestMapping("/gametables")
@Controller
public class GameTableController {

	@Autowired
    GameTableRepository gameTableRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    PlayedRepository playedRepository;

	@Autowired
    TimeLimitRepository timeLimitRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid GameTable gameTable, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameTable);
            return "gametables/create";
        }
        uiModel.asMap().clear();
        gameTableRepository.save(gameTable);
        return "redirect:/gametables/" + encodeUrlPathSegment(gameTable.getGameTableId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new GameTable());
        return "gametables/create";
    }

	@RequestMapping(value = "/{gameTableId}", produces = "text/html")
    public String show(@PathVariable("gameTableId") Integer gameTableId, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("gametable", gameTableRepository.findOne(gameTableId));
        uiModel.addAttribute("itemId", gameTableId);
        return "gametables/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("gametables", gameTableRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) gameTableRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("gametables", gameTableRepository.findAll());
        }
        addDateTimeFormatPatterns(uiModel);
        return "gametables/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid GameTable gameTable, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameTable);
            return "gametables/update";
        }
        uiModel.asMap().clear();
        gameTableRepository.save(gameTable);
        return "redirect:/gametables/" + encodeUrlPathSegment(gameTable.getGameTableId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{gameTableId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("gameTableId") Integer gameTableId, Model uiModel) {
        populateEditForm(uiModel, gameTableRepository.findOne(gameTableId));
        return "gametables/update";
    }

	@RequestMapping(value = "/{gameTableId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("gameTableId") Integer gameTableId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        GameTable gameTable = gameTableRepository.findOne(gameTableId);
        gameTableRepository.delete(gameTable);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/gametables";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("gameTable_createddtm_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("gameTable_gamestarteddtm_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("gameTable_gameendeddtm_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, GameTable gameTable) {
        uiModel.addAttribute("gameTable", gameTable);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        uiModel.addAttribute("playeds", playedRepository.findAll());
        uiModel.addAttribute("timelimits", timeLimitRepository.findAll());
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
