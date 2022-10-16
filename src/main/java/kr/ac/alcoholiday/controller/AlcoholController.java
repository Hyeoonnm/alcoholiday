package kr.ac.alcoholiday.controller;

import kr.ac.alcoholiday.model.Alcohol;
import kr.ac.alcoholiday.pager.Pager;
import kr.ac.alcoholiday.service.AlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/alcohol")

public class AlcoholController {

    @Autowired
    AlcoholService service;

    @RequestMapping("/list")
    public String list(Model model, Pager pager) {

        List<Alcohol> alcohol = service.list(pager);
        model.addAttribute("alcohol", alcohol);

        return "alcohol/list";
    }

    @GetMapping("/add")
    public String add() {
        return "alcohol/add";
    }


    @PostMapping("/add")
    public String add(Alcohol item) {
        service.add(item);

        return "redirect:./list";
    }

    @GetMapping("/update/{alcoholNum}")
    public String update(@PathVariable int alcoholNum, Model model) {
        Alcohol item = service.item(alcoholNum);

        model.addAttribute("item", item);

        return "alcohol/update";
    }

    @PostMapping("/update/{alcoholNum}")
    public String update(@PathVariable int alcoholNum, Alcohol item) {

        service.update(item);

        return "redirect:../list";
    }

    @RequestMapping("/delete/{alcoholNum}")
    public String delete(@PathVariable int alcoholNum) {

        service.delete(alcoholNum);

        return "redirect:../list";
    }
}
