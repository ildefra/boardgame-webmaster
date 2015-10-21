package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.AvgGameLength;
import org.m4.bgw.domain.AvgGameLengthPK;
import org.m4.bgw.domain.AvgGameLengthRepository;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.TimeLimitRepository;
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

@RequestMapping("/avggamelengths")
@Controller
public class AvgGameLengthController {

	private ConversionService conversionService;

	@Autowired
    AvgGameLengthRepository avgGameLengthRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    TimeLimitRepository timeLimitRepository;

	@Autowired
    public AvgGameLengthController(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid AvgGameLength avgGameLength, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, avgGameLength);
            return "avggamelengths/create";
        }
        uiModel.asMap().clear();
        avgGameLengthRepository.save(avgGameLength);
        return "redirect:/avggamelengths/" + encodeUrlPathSegment(conversionService.convert(avgGameLength.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new AvgGameLength());
        return "avggamelengths/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") AvgGameLengthPK id, Model uiModel) {
        uiModel.addAttribute("avggamelength", avgGameLengthRepository.findOne(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "avggamelengths/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("avggamelengths", avgGameLengthRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) avgGameLengthRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("avggamelengths", avgGameLengthRepository.findAll());
        }
        return "avggamelengths/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid AvgGameLength avgGameLength, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, avgGameLength);
            return "avggamelengths/update";
        }
        uiModel.asMap().clear();
        avgGameLengthRepository.save(avgGameLength);
        return "redirect:/avggamelengths/" + encodeUrlPathSegment(conversionService.convert(avgGameLength.getId(), String.class), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") AvgGameLengthPK id, Model uiModel) {
        populateEditForm(uiModel, avgGameLengthRepository.findOne(id));
        return "avggamelengths/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") AvgGameLengthPK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        AvgGameLength avgGameLength = avgGameLengthRepository.findOne(id);
        avgGameLengthRepository.delete(avgGameLength);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/avggamelengths";
    }

	void populateEditForm(Model uiModel, AvgGameLength avgGameLength) {
        uiModel.addAttribute("avgGameLength", avgGameLength);
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        uiModel.addAttribute("timelimits", timeLimitRepository.findAll());
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