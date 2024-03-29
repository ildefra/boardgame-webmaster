package org.m4.bgw.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.m4.bgw.domain.AvgGameLengthRepository;
import org.m4.bgw.domain.GameTableRepository;
import org.m4.bgw.domain.TimeLimit;
import org.m4.bgw.domain.TimeLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/timelimits")
@Controller
public class TimeLimitController {

	@Autowired
    TimeLimitRepository timeLimitRepository;

	@Autowired
    AvgGameLengthRepository avgGameLengthRepository;

	@Autowired
    GameTableRepository gameTableRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(
            @Valid TimeLimit timeLimit,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, timeLimit);
            return "timelimits/create";
        }
        timeLimitRepository.save(timeLimit);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/timelimits";
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new TimeLimit());
        return "timelimits/create";
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
            uiModel.addAttribute("timelimits", timeLimitRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) timeLimitRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("timelimits", timeLimitRepository.findAll());
        }
        return "timelimits/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(
            @Valid TimeLimit timeLimit,
            BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, timeLimit);
            return "timelimits/update";
        }
        timeLimitRepository.save(timeLimit);
        
        uiModel.asMap().clear();
        uiModel.addAttribute("page", "1");
        uiModel.addAttribute("size", "10");
        return "redirect:/timelimits";
    }

	@RequestMapping(value = "/{timeLimitId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("timeLimitId") Integer timeLimitId, Model uiModel) {
        populateEditForm(uiModel, timeLimitRepository.findOne(timeLimitId));
        return "timelimits/update";
    }

	@RequestMapping(value = "/{timeLimitId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(
            @PathVariable("timeLimitId") Integer timeLimitId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        TimeLimit timeLimit = timeLimitRepository.findOne(timeLimitId);
        timeLimitRepository.delete(timeLimit);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/timelimits";
    }

	void populateEditForm(Model uiModel, TimeLimit timeLimit) {
        uiModel.addAttribute("timeLimit", timeLimit);
        uiModel.addAttribute("avggamelengths", avgGameLengthRepository.findAll());
        uiModel.addAttribute("gametables", gameTableRepository.findAll());
    }
}
