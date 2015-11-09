package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.GameDesigner;
import org.m4.bgw.domain.GameDesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/gamedesigners")
@Controller
public class GameDesignerController {

	@Autowired
    GameDesignerRepository gameDesignerRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid GameDesigner gameDesigner,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameDesigner);
            return "gamedesigners/create";
        }
        gameDesignerRepository.save(gameDesigner);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/gamedesigners";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new GameDesigner());
        return "gamedesigners/create";
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
            uiModel.addAttribute("gamedesigners", gameDesignerRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) gameDesignerRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("gamedesigners", gameDesignerRepository.findAll());
        }
        return "gamedesigners/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(
            @Valid GameDesigner gameDesigner,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gameDesigner);
            return "gamedesigners/update";
        }
        gameDesignerRepository.save(gameDesigner);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/gamedesigners";
    }

	@RequestMapping(value = "/{gameDesignerId}", params = "form", produces = "text/html")
    public String updateForm(
            @PathVariable("gameDesignerId") Integer gameDesignerId, Model uiModel) {
        populateEditForm(uiModel, gameDesignerRepository.findOne(gameDesignerId));
        return "gamedesigners/update";
    }

	@RequestMapping(
	        value = "/{gameDesignerId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("gameDesignerId") Integer gameDesignerId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        GameDesigner gameDesigner = gameDesignerRepository.findOne(gameDesignerId);
        gameDesignerRepository.delete(gameDesigner);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/gamedesigners";
    }

	void populateEditForm(Model uiModel, GameDesigner gameDesigner) {
        uiModel.addAttribute("gameDesigner", gameDesigner);
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
    }
}
