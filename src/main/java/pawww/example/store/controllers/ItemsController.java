package pawww.example.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pawww.example.store.data.Category;
import pawww.example.store.data.Item;
import pawww.example.store.repositories.ItemRepository;
import pawww.example.store.services.StoreService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemsController {


    @Autowired
    //ItemRepository items;
    StoreService service;




    @GetMapping("/show")
    public String home() {
        System.out.println("in controller");
        return "items";
    }

    @GetMapping("/")
    public String getItems(Model model, HttpServletRequest request) {
        System.out.println("get items in controller "+this.service.getItems());
        model.addAttribute("items",this.service.getItems());
        model.addAttribute("categories",this.service.getCategories());
        return "items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        System.out.println("get one item in controller "+id+" "+this.service.getItem(id));
        model.addAttribute("item",this.service.getItem(id));
        return "item";

    }

    @GetMapping("/category")
    public String getItems(@RequestParam("name") String category, Model model) {
        System.out.println("get items from category "+category+" in controller "+this.service.getItemsFromCategory(category));
        model.addAttribute("items",this.service.getItemsFromCategory(category));
        model.addAttribute("category",category);
        model.addAttribute("categories",this.service.getCategories());
        return "items";
    }


//    @PostMapping("/add")
//    public String addItem(Model model) {
//        //@notempty
//       // Item item2add=new Item("milk",3.56f,this.items.getCategory("dairy"), this.items.getCategories());
//        Item item2add=new Item("milk",3.56f,this.items.getCategory("dairy"));
//        this.items.addItem(item2add);
//        System.out.println("add item in controller "+this.items);
//        model.addAttribute("items",this.items.getItems());
//        return "items";
//    }

//    @GetMapping(value={"/delete/{id}","/delete/"})
//    public String deleteItem(@PathVariable Optional<Integer> id, Model model) {
//        int iid= id.isPresent() ? id.get() : 0;
//        this.items.removeItem(iid);
//        System.out.println("remove item in controller "+this.service.getItems());
//        model.addAttribute("items",this.service.getItems());
//        return "items";
//    }

//    @GetMapping("/search")
//    public String searchItems(@RequestParam(value = "name", defaultValue = "") String name, Model model) {
//        if(!name.isEmpty()) model.addAttribute("items", this.items.searchItems(name));
//        model.addAttribute("query", name);
//        model.addAttribute("categories",this.items.getCategories());
//        return "items";
//
//    }

//    @GetMapping("/manage/add")
//    public String manage(Model model){
//        model.addAttribute("categories",this.items.getCategories());
//        model.addAttribute("newItem", new Item());
//        //model.addAttribute("newItemCat", new Category());
//        return "add_item";
//    }
//
//    @PostMapping("/manage/add")
//    public String managePost(@Valid @ModelAttribute("newItem") Item newItem, /*Errors*/ BindingResult result, Model model) {
//        System.out.println("resuult "+result.hasErrors());
//            if (result.hasErrors()) {
//                System.out.println(result.getErrorCount());
//                result.getAllErrors().forEach(el-> System.out.println(el));
//                model.addAttribute("categories",this.items.getCategories());
//                return "add_item";
//            }
//            this.items.addItem(newItem);
//        return "redirect:/items/";
//
//    }
//
//    @GetMapping("/category/add")
//    public String manageCat(Model model){
//        model.addAttribute("newCategory", new Category());
//        return "add_category";
//    }
//
//    @PostMapping("/category/add")
//    public String manageCatPost(@Valid @ModelAttribute("newCategory") Category newCategory, /*Errors*/ BindingResult result, Model model) {
//        System.out.println("resuult "+result.hasErrors()+" "+newCategory.getCat_name());
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(el-> System.out.println(el));
//            return "add_category";
//        }
//        this.items.addCategory(newCategory);
//        return "redirect:/items/";
//
//    }


    }
