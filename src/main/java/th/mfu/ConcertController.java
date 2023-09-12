package th.mfu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConcertController {
    // TODO: create hashmap of concerts for storing data

    private HashMap<Integer, Concert> concerts = new HashMap<>();
    private int nextId = 1;

    //TODO: add initbinder to convert date

 @InitBinder

    public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
}


    @GetMapping("/concerts")
    public String listConcerts(Model model) {      
        model.addAttribute("concerts", concerts.value());
        return "list-concerts";
    }

    @GetMapping("/add-concert")
    public String addAConcertForm(Model model) {
        model.addAttribute("concert", new Concert());
        return "concert-form";
    }

    @PostMapping("/concerts")
    public String saveConcert(@ModelAttribute Concert concert) {
        concert.setId(nextId++);
        concerts.put(concert.getId(), concert);
        return "redirect:/concerts";
    }

    @GetMapping("/delete-concert/{id}")
    public String deleteConcert(@PathVariable int id) {
        concerts.remove(id);
        return "redirect:/concerts";
    }

   
    @GetMapping("/delete-concert")
    public String removeAllConcerts() {
        concerts.clear();
        nextId = 1;
        return "redirect:/concerts";
    }

}  
