package com.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.system.dao.MenuItemDAO;
import com.system.entity.MenuItem;

import java.util.List;

@SpringBootApplication
@RestController
public class MenuItemController {

    @Autowired
    private MenuItemDAO menuitemDAO;

    @GetMapping("/menuItems")
    public List<MenuItem> getMenItems() {
        return (List<MenuItem>) menuitemDAO.findAll();
    }

    @GetMapping("/menuItems/{id}")
    public MenuItem findMenuById(@PathVariable("id") int id) {
        return menuitemDAO.findOne(id);
    }

    @PostMapping("/menuItems")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<MenuItem> menuItemList) {
        menuitemDAO.save(menuItemList);
    }

    @DeleteMapping("/menuItems")
    public void deleteAll() {
        menuitemDAO.deleteAll();
    }

    @DeleteMapping("/menuItems/{id}")
    public void deleteById(@PathVariable("id") int id) {
        menuitemDAO.delete(id);
    }

}

