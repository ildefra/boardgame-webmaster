package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.GameTableRepository;
import org.m4.bgw.domain.Played;
import org.m4.bgw.domain.PlayedPK;
import org.m4.bgw.domain.PlayedRepository;
import org.m4.bgw.domain.PlayerRepository;
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

@RequestMapping("/playeds")
@Controller
public class PlayedController {

	private ConversionService conversionService;

	@Autowired
    PlayedRepository playedRepository;

	@Autowired
    GameTableRepository gameTableRepository;

	@Autowired
    PlayerRepository playerRepository;

	@Autowired
    public PlayedController(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Played played, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, played);
            return "playeds/create";
        }
        uiModel.asMap().clear();
        playedRepository.save(played);
        return "redirect:/playeds/" + encodeUrlPathSegment(conversionService.convert(played.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Played());
        return "playeds/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") PlayedPK id, Model uiModel) {
        uiModel.addAttribute("played", playedRepository.findOne(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "playeds/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("playeds", playedRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) playedRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("playeds", playedRepository.findAll());
        }
        return "playeds/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Played played, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, played);
            return "playeds/update";
        }
        uiModel.asMap().clear();
        playedRepository.save(played);
        return "redirect:/playeds/" + encodeUrlPathSegment(conversionService.convert(played.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") PlayedPK id, Model uiModel) {
        populateEditForm(uiModel, playedRepository.findOne(id));
        return "playeds/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") PlayedPK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Played played = playedRepository.findOne(id);
        playedRepository.delete(played);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/playeds";
    }

	void populateEditForm(Model uiModel, Played played) {
        uiModel.addAttribute("played", played);
        uiModel.addAttribute("gametables", gameTableRepository.findAll());
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
