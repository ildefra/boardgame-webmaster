package org.m4.bgw.web;

import org.m4.bgw.domain.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/countrys")
@Controller
public class CountryController {

	@Autowired
    CountryRepository countryRepository;

    @RequestMapping(value = "/{isoAlpha2}", produces = "text/html")
    public String show(@PathVariable("isoAlpha2") String isoAlpha2, Model uiModel) {
        uiModel.addAttribute("country", countryRepository.findOne(isoAlpha2));
        uiModel.addAttribute("itemId", isoAlpha2);
        return "countrys/show";
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
            uiModel.addAttribute(
                    "countrys",
                    countryRepository.findAll(new PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) countryRepository.count() / sizeNo;
            uiModel.addAttribute(
                    "maxPages",
                    (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("countrys", countryRepository.findAll());
        }
        return "countrys/list";
    }
}
