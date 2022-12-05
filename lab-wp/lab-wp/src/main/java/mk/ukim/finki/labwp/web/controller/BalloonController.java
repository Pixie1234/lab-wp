package mk.ukim.finki.labwp.web.controller;
import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Manufacturer;
import mk.ukim.finki.labwp.service.BalloonService;
import mk.ukim.finki.labwp.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private  final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/balloons")
public String getBalloonsPage(@RequestParam(required = false)  Model model){

        List<Balloon> balloons = this.balloonService.listAll();
        model.addAttribute("balloons", balloons);

        return "listBalloons";
}

@PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer){
    this.balloonService.save(name,description, manufacturer);
    //return "listBalloons";
    return "redirect:/balloons";

    }

@DeleteMapping ("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
    List<Balloon> balloons = this.balloonService.listAll();
        return "redirect:/balloons";

    }
@GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        if (this.balloonService.findById(id).isPresent()){
            Balloon balloon=this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers=this.manufacturerService.findAll();
            List<Balloon>balloons=this.balloonService.listAll();

            //model.addAttribute("balloons", balloons);
            model.addAttribute("balloon", balloon);
            model.addAttribute("description",balloon.getDescription());
            model.addAttribute("manufacturers", manufacturers);
            return "add-balloon";

        }
    return "redirect:/balloons";
    //return "listBalloons";
}
@GetMapping("/add-form")
    public String getAddBalloonPage( Model model){
    List<Manufacturer> manufacturers = this.manufacturerService.findAll();
    List<Balloon> balloons = this.balloonService.listAll();
    model.addAttribute("manufacturers", manufacturers);
    model.addAttribute("balloons", balloons);

    //model.addAttribute("description",balloons);
    return "add-balloon";


}



}
