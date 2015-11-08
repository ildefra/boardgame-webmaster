package org.m4.bgw.web;

import org.m4.bgw.domain.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/languages")
@Controller
public class LanguageController {

	@Autowired
    LanguageRepository languageRepository;

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
            
            PageRequest pagedAndSorted =
                    new PageRequest(firstResult / sizeNo, sizeNo, Direction.ASC, "isoCode");
            uiModel.addAttribute(
                    "languages", languageRepository.findAll(pagedAndSorted).getContent());
            
            float nrOfPages = (float) languageRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("languages", languageRepository.findAll());
        }
        return "languages/list";
    }
}
