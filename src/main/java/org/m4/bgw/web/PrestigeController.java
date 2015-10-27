package org.m4.bgw.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.m4.bgw.domain.Achieved;
import org.m4.bgw.domain.Player;
import org.m4.bgw.domain.PlayerRepository;
import org.m4.bgw.report.PrestigeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/prestige")
@Controller
public class PrestigeController {

	@Autowired
    PlayerRepository playerRepository;

	@RequestMapping(produces = "text/html")
    public String list(Model uiModel) {
	    List<PrestigeVO> items = new ArrayList<>();
	    List<Player> players = playerRepository.findAll();
	    for (Player player : players) {
	        int points = 0;
	        for (Achieved achieved : player.getAchieveds()) {
	            points += achieved.getAchievementId().getPoints();
	        }
	        items.add(new PrestigeVO(player.getCountry(), player.getUsername(), points));
	    }
	    Collections.sort(items);
        uiModel.addAttribute("items", items);
        return "reports/prestige";
    }
}
