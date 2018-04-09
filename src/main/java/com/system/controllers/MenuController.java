package com.system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.dao.MenuDAO;
import com.system.dao.MenuItemDAO;
import com.system.entity.Menu;
import com.system.entity.MenuItem;

@SpringBootApplication
@RestController
public class MenuController {

    @Autowired
    private MenuDAO menuDAO;

    @Autowired
    private MenuItemDAO menuitemDAO;

    @GetMapping("/menus")
    public List<Menu> getMenus() {
        return menuDAO.findAll();
    }

    @GetMapping("/menus/{id}")
    public Menu findMenuById(@PathVariable("id") int id) {
        return menuDAO.findOne(id);
    }

    @PostMapping("/menus")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(Menu menuList) {
        menuDAO.save(menuList);
    }

    @DeleteMapping("/menus/")
    public void deleteAll() {
        //log.info("Delete all menus: ");
        menuDAO.deleteAll();
    }

    @DeleteMapping("/menus/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
       // log.info("Delte menu by id :" + id);
        menuDAO.delete(id);
    }

    @GetMapping("/menus/{id}/items/")
    public List<MenuItem> getItems(@PathVariable("id") int id) {
        Menu menu = menuDAO.findOne(id);
        if (menu == null)
            return null;
        return menuitemDAO.findByMenuMenuId(id);
    }

  /*  @PostMapping("/menus/{id}/items/")
    public void addItems(@PathVariable("id") int id,@RequestBody List<MenuItem> items) {
        Menu menu = menuDAO.findOne(id);
        if (menu == null)
            return ;

        for (MenuItem item : items)
            item.setMenu(menu);

        menuitemDAO.save(items);
    }*/
}
