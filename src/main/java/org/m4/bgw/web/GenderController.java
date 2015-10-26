package org.m4.bgw.web;

import org.m4.bgw.domain.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/genders")
@Controller
public class GenderController {

	@Autowired
    GenderRepository genderRepository;

	@RequestMapping(value = "/{isoCode}", produces = "text/html")
    public String show(@PathVariable("isoCode") Short isoCode, Model uiModel) {
        uiModel.addAttribute("gender", genderRepository.findOne(isoCode));
        uiModel.addAttribute("itemId", isoCode);
        return "genders/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            
            PageRequest pagedAndSorted =
                    new PageRequest(firstResult / sizeNo, sizeNo, Direction.ASC, "isoCode");
            uiModel.addAttribute(
                    "genders", genderRepository.findAll(pagedAndSorted).getContent());

            float nrOfPages = (float) genderRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("genders", genderRepository.findAll());
        }
        return "genders/list";
    }
}
