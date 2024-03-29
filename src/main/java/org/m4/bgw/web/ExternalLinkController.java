package org.m4.bgw.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.LanguageRepository;
import org.m4.bgw.domain.translate.ExternalLink;
import org.m4.bgw.domain.translate.ExternalLinkRepository;
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

@RequestMapping("/externallinks")
@Controller
public class ExternalLinkController {

	@Autowired
    ExternalLinkRepository externalLinkRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    LanguageRepository languageRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid ExternalLink externalLink, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, externalLink);
            return "externallinks/create";
        }
        uiModel.asMap().clear();
        externalLinkRepository.save(externalLink);
        return "redirect:/externallinks/" + encodeUrlPathSegment(externalLink.getExternalLinkId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new ExternalLink());
        return "externallinks/create";
    }

	@RequestMapping(value = "/{externalLinkId}", produces = "text/html")
    public String show(@PathVariable("externalLinkId") Integer externalLinkId, Model uiModel) {
        uiModel.addAttribute("externallink", externalLinkRepository.findOne(externalLinkId));
        uiModel.addAttribute("itemId", externalLinkId);
        return "externallinks/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("externallinks", externalLinkRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) externalLinkRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("externallinks", externalLinkRepository.findAll());
        }
        return "externallinks/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid ExternalLink externalLink, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, externalLink);
            return "externallinks/update";
        }
        uiModel.asMap().clear();
        externalLinkRepository.save(externalLink);
        return "redirect:/externallinks/" + encodeUrlPathSegment(externalLink.getExternalLinkId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{externalLinkId}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("externalLinkId") Integer externalLinkId, Model uiModel) {
        populateEditForm(uiModel, externalLinkRepository.findOne(externalLinkId));
        return "externallinks/update";
    }

	@RequestMapping(value = "/{externalLinkId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("externalLinkId") Integer externalLinkId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ExternalLink externalLink = externalLinkRepository.findOne(externalLinkId);
        externalLinkRepository.delete(externalLink);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/externallinks";
    }

	void populateEditForm(Model uiModel, ExternalLink externalLink) {
        uiModel.addAttribute("externalLink", externalLink);
        uiModel.addAttribute("boardgames", boardgameRepository.findAll());
        uiModel.addAttribute("languages", languageRepository.findAll());
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
