package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.AchievedRepository;
import org.m4.bgw.domain.AvgGameLengthRepository;
import org.m4.bgw.domain.Boardgame;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.GameDesignerRepository;
import org.m4.bgw.domain.GameTableRepository;
import org.m4.bgw.domain.GameTagRepository;
import org.m4.bgw.domain.PlayerRepository;
import org.m4.bgw.domain.PublisherRepository;
import org.m4.bgw.domain.translate.ExternalLinkRepository;
import org.m4.bgw.domain.translate.GameTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/boardgames")
@Controller
public class BoardgameController {

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    AchievedRepository achievedRepository;

	@Autowired
    AvgGameLengthRepository avgGameLengthRepository;

	@Autowired
    ExternalLinkRepository externalLinkRepository;

	@Autowired
    GameDesignerRepository gameDesignerRepository;

	@Autowired
    GameTableRepository gameTableRepository;

	@Autowired
    GameTagRepository gameTagRepository;

	@Autowired
    GameTranslationRepository gameTranslationRepository;

	@Autowired
    PlayerRepository playerRepository;

	@Autowired
    PublisherRepository publisherRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid Boardgame boardgame,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, boardgame);
            return "boardgames/create";
        }
        boardgameRepository.save(boardgame);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/boardgames";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Boardgame());
        return "boardgames/create";
    }

	@RequestMapping(value = "/{boardgameId}", produces = "text/html")
    public String show(@PathVariable("boardgameId") Integer boardgameId, Model uiModel) {
        uiModel.addAttribute("boardgame", boardgameRepository.findOne(boardgameId));
        uiModel.addAttribute("itemId", boardgameId);
        return "boardgames/show";
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
                    "boardgames",
                    boardgameRepository.findAll(new PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) boardgameRepository.count() / sizeNo;
            uiModel.addAttribute(
                    "maxPages",
                    (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        }
        return "boardgames/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Boardgame boardgame, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, boardgame);
            return "boardgames/update";
        }
        boardgameRepository.save(boardgame);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/boardgames";
    }

	@RequestMapping(value = "/{boardgameId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("boardgameId") Integer boardgameId, Model uiModel) {
        populateEditForm(uiModel, boardgameRepository.findOne(boardgameId));
        return "boardgames/update";
    }

	@RequestMapping(
	        value = "/{boardgameId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("boardgameId") Integer boardgameId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        Boardgame boardgame = boardgameRepository.findOne(boardgameId);
        boardgameRepository.delete(boardgame);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/boardgames";
    }

	void populateEditForm(Model uiModel, Boardgame boardgame) {
        uiModel.addAttribute("boardgame", boardgame);
        uiModel.addAttribute("achieveds", achievedRepository.findAll());
        uiModel.addAttribute("avggamelengths", avgGameLengthRepository.findAll());
        uiModel.addAttribute("externallinks", externalLinkRepository.findAll());
        uiModel.addAttribute("gamedesigners", gameDesignerRepository.findAll());
        uiModel.addAttribute("gametables", gameTableRepository.findAll());
        uiModel.addAttribute("gametags", gameTagRepository.findAll());
        uiModel.addAttribute("gametranslations", gameTranslationRepository.findAll());
        uiModel.addAttribute("players", playerRepository.findAll());
        uiModel.addAttribute("publishers", publisherRepository.findAll());
    }
}
