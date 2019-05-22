package compparts.controller;

import compparts.model.Part;
import compparts.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class PartsController {
    private PartsService partsService;
    private int page;

    @Autowired
    public void setPartsService(PartsService partsService) {
        this.partsService = partsService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allParts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "DEFAULT") String filterTypeName) {
        this.page = page;
        FilterType filterType = FilterType.valueOf(filterTypeName);
        List<Part> parts = partsService.allParts(page, filterType);
        int partsCount = partsService.partsCount();
        int pagesCount = (partsCount + 9) / 10;
        int compsCount = partsService.compsCount();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("compparts");
        modelAndView.addObject("page", page);
        modelAndView.addObject("partsList", parts);
        modelAndView.addObject("partsCount", partsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("compsCount", compsCount);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        partsService.add(part);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Part part = partsService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("part", part);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        partsService.edit(part);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePart(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        Part part = partsService.getById(id);
        partsService.delete(part);
        return modelAndView;
    }

    @RequestMapping(value = "/search={searchString}", method = RequestMethod.GET)
    public ModelAndView searchPage(@PathVariable("searchString") String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Part> resultParts = partsService.findParts(searchString);
        modelAndView.setViewName("searchPage");
        modelAndView.addObject("resultParts", resultParts);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchPart(@RequestParam("searchString") String searchString) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/search=" + URLEncoder.encode(searchString, "UTF-8"));
        return modelAndView;
    }
}