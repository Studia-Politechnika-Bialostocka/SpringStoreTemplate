package pawww.example.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pawww.example.store.db.Item;
import pawww.example.store.db.repositories.ItemRepositoryDB;
import pawww.example.store.services.StoreService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

//   @Autowired
//   ItemRepository cart;

    //(3)
    @Autowired
    StoreService service;

    @Autowired
    ServletContext context;


    @GetMapping("/")
    public String getItems(Model model, HttpSession session, HttpServletRequest request) {
        System.out.println("get cart in controller "+this.service.getCart().getItems());
        model.addAttribute("cart",this.service.getCart());
        System.out.println("cart "+this.service.getCart());
        session.setAttribute("mostPopularCategories", this.service.getMostPopularCategories());
        System.out.println(request.getServletContext().getAttribute("minimumOrderValue"));
        model.addAttribute("prices",this.service.getCart().getPrices());
        return "cart";
    }


    @GetMapping("/add/{id}")
    public String addItemToCart(@PathVariable int id, Model model, HttpServletRequest request) {//throws Exception {
            Item item2add=service.getItem(id);
            System.out.println("Item with id " + id + ": " + item2add.getName());
            if(item2add==null) throw new DataNotFoundException(String.valueOf(id));
            //if(item2add==null) throw new Exception();
            this.service.getCart().addItem(item2add);
            System.out.println("add item to cart in controller " + item2add);
            model.addAttribute("cart", this.service.getCart().getItems());

            return "redirect:/cart/";
    }

    @GetMapping("/addmore/{name}")
    public String addMoreItemToCart(@PathVariable String name, Model model, HttpServletRequest request) {
        Item item2add=service.isInCart(name);
        if(item2add==null) throw new DataNotFoundException(name);
        this.service.getCart().addItem(item2add);
        System.out.println("add item to cart in controller " + item2add);
        model.addAttribute("cart", this.service.getCart().getItems());

        return "redirect:/cart/";
    }

    @GetMapping("/remove/{name}")
    public String removeItemFromCart(@PathVariable String name, Model model, HttpServletRequest request) {//throws Exception {
        Item item2add=service.isInCart(name);
        if(item2add==null) throw new DataNotFoundException(name);
        this.service.getCart().removeItem(item2add.getId());
        System.out.println("remove item  ");
        model.addAttribute("cart", this.service.getCart().getItems());

        return "redirect:/cart/";
//        }catch (Exception e){
//            System.out.println("error "+e.getMessage());
//            model.addAttribute("errorMsg",e.getMessage());
//            return "error";
//        }
    }

    @GetMapping("/buy/")
    public String buy(Model model){
        model.addAttribute("amount",this.service.getCartValue());
        this.service.buy();
        return "thankyou";
    }


//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No requested data")
//    public void handleDataError() {
//        System.out.println("ERROR");
//    }
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No requested data")
    public String handleDataError(DataNotFoundException e) {
        System.out.println("ERROR "+e.getMessage() );
        return "error/notfound";
    }
}

