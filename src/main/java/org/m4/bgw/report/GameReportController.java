package org.m4.bgw.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.m4.bgw.domain.Boardgame;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.GameTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/games")
@Controller
public class GameReportController {

	@Autowired
    BoardgameRepository gameRepository;

	@RequestMapping(produces = "text/html")
    public String list(Model uiModel) {
	    List<GameReportVO> items = new ArrayList<>();
	    for (Boardgame game : gameRepository.findAll()) {	        
	        Set<GameTable> tables = game.getGameTables();
	        Double avgLength = tables.stream().mapToInt(
	                table -> Minutes.minutesBetween(
	                        new DateTime(table.getGameStartedDtm().getTime()),
	                        new DateTime(table.getGameEndedDtm().getTime())).getMinutes()
	                ).average().orElse(0.0);
	        items.add(new GameReportVO(game, tables.size(), avgLength.intValue()));
	    }
	    Collections.sort(items);
        uiModel.addAttribute("items", items);
        return "reports/games";
    }
}
