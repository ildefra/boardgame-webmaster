package org.m4.bgw.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.m4.bgw.domain.Achieved;
import org.m4.bgw.domain.Player;
import org.m4.bgw.domain.PlayerRepository;
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
	    for (Player player : playerRepository.findAll()) {
	        int points = 0;
	        for (Achieved achieved : player.getAchieveds()) {
	            points += achieved.getAchievement().getPoints();
	        }
	        items.add(new PrestigeVO(player.getCountry(), player.getUsername(), points));
	    }
	    Collections.sort(items);
        uiModel.addAttribute("items", items);
        return "reports/prestige";
    }
}
