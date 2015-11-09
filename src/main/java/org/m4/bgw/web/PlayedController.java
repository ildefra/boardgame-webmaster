package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.GameTableRepository;
import org.m4.bgw.domain.Played;
import org.m4.bgw.domain.PlayedRepository;
import org.m4.bgw.domain.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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
    public String create(
            @Valid Played played,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, played);
            return "playeds/create";
        }
        playedRepository.save(played);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/playeds";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Played());
        return "playeds/create";
    }

	@RequestMapping(value = "/{playedId}", produces = "text/html")
    public String show(@PathVariable("playedId") Integer playedId, Model uiModel) {
        uiModel.addAttribute("played", playedRepository.findOne(playedId));
        uiModel.addAttribute("itemId", conversionService.convert(playedId, String.class));
        return "playeds/show";
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
            
            PageRequest pagedAndSorted =
                    new PageRequest(firstResult / sizeNo, sizeNo, Direction.ASC, "gameTableId");
            uiModel.addAttribute(
                    "playeds", playedRepository.findAll(pagedAndSorted).getContent());
            
            float nrOfPages = (float) playedRepository.count() / sizeNo;
            uiModel.addAttribute(
                    "maxPages",
                    (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("playeds", playedRepository.findAll());
        }
        return "playeds/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(
            @Valid Played played,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, played);
            return "playeds/update";
        }
        playedRepository.save(played);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/playeds";
    }

	@RequestMapping(value = "/{playedId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("playedId") Integer playedId, Model uiModel) {
        populateEditForm(uiModel, playedRepository.findOne(playedId));
        return "playeds/update";
    }

	@RequestMapping(
	        value = "/{playedId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("playedId") Integer playedId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        Played played = playedRepository.findOne(playedId);
        playedRepository.delete(played);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/playeds";
    }

	void populateEditForm(Model uiModel, Played played) {
        uiModel.addAttribute("played", played);
        uiModel.addAttribute("gametables",  gameTableRepository.findAll());
        uiModel.addAttribute("players",     playerRepository.findAll());
    }
}
