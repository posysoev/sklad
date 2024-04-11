package com.sklad.sklad.controllers;

import com.sklad.sklad.models.Goods;
import com.sklad.sklad.repo.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TableController {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("/table")
    public String tableMain(Model model){
        Iterable<Goods> goods = goodsRepository.findAll();
        model.addAttribute("goods", goods);
        model.addAttribute("title", "Список  товаров");
        return "table-main";
    }

    //Add
    @GetMapping("/table/add-goods")
    public String addGoods(Model model){
        model.addAttribute("title", "Поставка товара");
        return "add-goods";
    }

    @PostMapping("/table/add-goods")
    public String addGood(@RequestParam String name, @RequestParam String price_buy, @RequestParam String price_sell, @RequestParam String count, Model model){
        Goods goods = new Goods(name, price_buy, price_sell, count);
        goodsRepository.save(goods);
        return "redirect:/table";
    }

    @GetMapping("/table/{id}/edit")
    public String GoodEdit(@PathVariable(value = "id") long id, Model model){
        if (!goodsRepository.existsById(id)){
            return "redirect:/table";
        }
        Optional<Goods> good = goodsRepository.findById(id);
        ArrayList<Goods> res = new ArrayList<>();
        good.ifPresent(res::add);
        model.addAttribute("goods",res);
        return "good-edit";
    }

    @PostMapping("/table/{id}/edit")
    public String goodEdit(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String price_buy, @RequestParam String price_sell, @RequestParam String count, Model model){
        Goods good = goodsRepository.findById(id).orElseThrow();
        good.setCount(Integer.parseInt(count));
        good.setName(name);
        good.setPriceBuy(Double.parseDouble(price_buy));
        good.setPriceSell(Double.parseDouble(price_sell));
        goodsRepository.save(good);
        return "redirect:/table";
    }

    @PostMapping("/table/{id}/delete")
    public String deleteGood(@PathVariable(value = "id") long id, Model model){
        Goods good = goodsRepository.findById(id).orElseThrow();
        goodsRepository.delete(good);
        return "redirect:/table";
    }
}
