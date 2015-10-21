package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.AchievedRepository;
import org.m4.bgw.domain.Achievement;
import org.m4.bgw.domain.AchievementRepository;
import org.m4.bgw.domain.AchievementTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/achievements")
@Controller
public class AchievementController {

	@Autowired
    AchievementRepository achievementRepository;

	@Autowired
    AchievedRepository achievedRepository;

	@Autowired
    AchievementTranslationRepository achievementTranslationRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Achievement achievement, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achievement);
            return "achievements/create";
        }
        uiModel.asMap().clear();
        achievementRepository.save(achievement);
        return "redirect:/achievements/" + encodeUrlPathSegment(achievement.getAchievementId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Achievement());
        return "achievements/create";
    }

	@RequestMapping(value = "/{achievementId}", produces = "text/html")
    public String show(@PathVariable("achievementId") Short achievementId, Model uiModel) {
        uiModel.addAttribute("achievement", achievementRepository.findOne(achievementId));
        uiModel.addAttribute("itemId", achievementId);
        return "achievements/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("achievements", achievementRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) achievementRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("achievements", achievementRepository.findAll());
        }
        return "achievements/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Achievement achievement, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, achievement);
            return "achievements/update";
        }
        uiModel.asMap().clear();
        achievementRepository.save(achievement);
        return "redirect:/achievements/" + encodeUrlPathSegment(achievement.getAchievementId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{achievementId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("achievementId") Short achievementId, Model uiModel) {
        populateEditForm(uiModel, achievementRepository.findOne(achievementId));
        return "achievements/update";
    }

	@RequestMapping(value = "/{achievementId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("achievementId") Short achievementId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Achievement achievement = achievementRepository.findOne(achievementId);
        achievementRepository.delete(achievement);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/achievements";
    }

	void populateEditForm(Model uiModel, Achievement achievement) {
        uiModel.addAttribute("achievement", achievement);
        uiModel.addAttribute("achieveds", achievedRepository.findAll());
        uiModel.addAttribute("achievementtranslations", achievementTranslationRepository.findAll());
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
