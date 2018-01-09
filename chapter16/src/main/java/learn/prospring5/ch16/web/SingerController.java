package learn.prospring5.ch16.web;


import com.google.common.collect.Lists;
import learn.prospring5.ch16.entities.Singer;
import learn.prospring5.ch16.services.SingerService;
import learn.prospring5.ch16.utils.Message;
import learn.prospring5.ch16.utils.SingerGrid;
import learn.prospring5.ch16.utils.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@RequestMapping("/singers")
@Controller
public class SingerController {
    private final Logger logger = LoggerFactory.getLogger(SingerController.class);

    private SingerService singerService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing singers");

        List<Singer> singers = singerService.findAll();
        uiModel.addAttribute("singers", singers);
        logger.info("No. of singers: " + singers.size());
        return "singers/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Singer singer = singerService.findById(id);
        uiModel.addAttribute("singer", singer);
        return "singers/show";
    }


    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Singer singer,
                         BindingResult bindingResult,
                         Model uiModel,
                         HttpServletRequest httpServletRequest,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Updating singer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("singer", singer);
            return "singers/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("singer_save_success", new Object[]{}, locale)));
        singerService.save(singer);
        return "redirect:/singers/" + UrlUtil.encodeUrlPathSegment(
                singer.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("singer", singerService.findById(id));
        return "singers/update";
    }

    @RequestMapping(method  =  RequestMethod.POST)
    public  String  create(@Valid Singer singer,  BindingResult bindingResult,
                           Model   uiModel, HttpServletRequest httpServletRequest,
                           RedirectAttributes redirectAttributes,Locale locale) {
        logger.info("Creating singer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("singer", singer);
            return "singers/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("singer_save_success", new Object[]{}, locale)));
        logger.info("Singer id:  " +  singer.getId());
        singerService.save(singer);
        return "redirect:/singers/";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        Singer singer =  new Singer();
        uiModel.addAttribute("singer", singer);
        return "singers/create";
    }


    @ResponseBody
    @RequestMapping(value =  "/listgrid", method  =  RequestMethod.GET, produces="application/json")
    public SingerGrid listGrid(@RequestParam(value =  "page", required =  false)  Integer page,
                               @RequestParam(value =  "rows", required =  false)  Integer rows,
                               @RequestParam(value =  "sidx",  required =  false) String sortBy,
                               @RequestParam(value =  "sord",  required =  false) String order) {
        logger.info("Listing  singers for  grid with page: {}, rows: {}", page, rows);
        logger.info("Listing singers for grid with sort:  {}, order: {}", sortBy, order);
        // Process order  by
        Sort sort =  null;
        String  orderBy =  sortBy;
        if (orderBy != null  && orderBy.equals("birthDateString"))
            orderBy =  "birthDate";
        if (orderBy != null  && order != null)  {
            if (order.equals("desc")) {
                sort =  new Sort(Sort.Direction.DESC, orderBy);
            } else
                sort =  new Sort(Sort.Direction.ASC, orderBy);
        }
        // Constructs page  request for  current  page
        // Note: page  number  for  Spring Data   JPA starts with 0,
        // while jqGrid starts  with 1
        PageRequest pageRequest  =  null;
        if (sort != null) {
            pageRequest =  PageRequest.of(page - 1, rows, sort);
        } else {
            pageRequest =  PageRequest.of(page - 1, rows);
        }
        Page<Singer> singerPage  =  singerService.findAllByPage(pageRequest);
        // Construct the grid  data that  will  return as  JSON data
        SingerGrid singerGrid =  new SingerGrid();
        singerGrid.setCurrentPage(singerPage.getNumber() +  1);
        singerGrid.setTotalPages(singerPage.getTotalPages());
        singerGrid.setTotalRecords(singerPage.getTotalElements());
        singerGrid.setSingerData(Lists.newArrayList(singerPage.iterator()));
        return singerGrid;
    }


    @Autowired
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }

    @Autowired
    public void setMessageSource(MessageSource  messageSource) {
        this.messageSource =  messageSource;
    }
}