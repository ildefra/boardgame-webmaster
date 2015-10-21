package org.m4.bgw.web;
import org.m4.bgw.domain.Language;
import org.m4.bgw.domain.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.annotations.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/languages")
@Controller
@RooWebScaffold(path = "languages", formBackingObject = Language.class, create = false, update = false, delete = false)
public class LanguageController {

	@Autowired
    LanguageRepository languageRepository;

	@RequestMapping(value = "/{isoCode}", produces = "text/html")
    public String show(@PathVariable("isoCode") String isoCode, Model uiModel) {
        uiModel.addAttribute("language", languageRepository.findOne(isoCode));
        uiModel.addAttribute("itemId", isoCode);
        return "languages/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("languages", languageRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) languageRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("languages", languageRepository.findAll());
        }
        return "languages/list";
    }
}
