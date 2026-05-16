package com.example.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Entity.Item;
import com.example.Backend.Service.Itemservice;

@RestController
@CrossOrigin(origins = "https://govindanvegetables.netlify.app")
@RequestMapping("item")
public class Itemcontrolller {
    
@Autowired
private Itemservice itemservice;

    @PostMapping("register")
    public String additems(@RequestBody Item item){
      return itemservice.additems(item);
    }

    @GetMapping("items")
    public List<Item> getitems(){
              return itemservice.getitems();
    }
    
    @PutMapping("{id}")
    public String updateitems(@PathVariable Long id,@RequestBody Item item){
      return itemservice.updateitems(id,item);
    }
    @DeleteMapping("{id}")
    public String deleteitems(@PathVariable Long id){
      return itemservice.deleteitems(id);
    }
    @GetMapping("search")
    public List<Item>  tangilshsearch(@RequestParam String query){
             return  itemservice.tangilshsearch(query);
    }
  }


  