package org.m4.bgw.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.m4.bgw.domain.Boardgame;
import org.m4.bgw.domain.BoardgameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/prestige")
@Controller
public class GameReportController {

	@Autowired
    BoardgameRepository gameRepository;

	@RequestMapping(produces = "text/html")
    public String list(Model uiModel) {
	    List<GameReportVO> items = new ArrayList<>();
	    for (Boardgame game : gameRepository.findAll()) {
	        items.add(new GameReportVO(game, game.getGameTables().size()));
	    }
	    Collections.sort(items);
        uiModel.addAttribute("items", items);
        return "reports/games";
    }
}
