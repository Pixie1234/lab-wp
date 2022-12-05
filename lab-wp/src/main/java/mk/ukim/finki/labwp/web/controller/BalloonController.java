package mk.ukim.finki.labwp.web.controller;
import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Manufacturer;
import mk.ukim.finki.labwp.service.BalloonService;
import mk.ukim.finki.labwp.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }



        List<Balloon> balloons = this.balloonService.listAll();
        model.addAttribute("balloons", balloons);

        model.addAttribute("bodyContent","listBalloons");
        return "master-template";




}

@PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id,
                            @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer){

        if (id != null){
            this.balloonService.edit(id,name,description,manufacturer);
        }
        else {
            this.balloonService.save(name,description, manufacturer);
        }

   // return "listBalloons";
    return "redirect:/balloons";

    }

@DeleteMapping ("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
    //List<Balloon> balloons = this.balloonService.listAll();
        return "redirect:/balloons";

    }
@GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        if (this.balloonService.findById(id).isPresent()){
            Balloon balloon=this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers=this.manufacturerService.findAll();
            List<Balloon>balloons=this.balloonService.listAll();

           model.addAttribute("id", id);
            model.addAttribute("balloon", balloon);
            model.addAttribute("description",balloon.getDescription());
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("bodyContent","add-balloon");
            return "master-template";

        }
    //return "redirect:/balloons?error=BalloonNotFound";
    return "redirect:/balloons";

}
@GetMapping("/add-form")
@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage( Model model){
    List<Manufacturer> manufacturers = this.manufacturerService.findAll();
    List<Balloon> balloons = this.balloonService.listAll();
    model.addAttribute("manufacturers", manufacturers);
    //model.addAttribute("balloons", balloons);
   model.addAttribute("bodyContent","add-balloon");

    //model.addAttribute("description",balloons);
   return "master-template";
   // return "listBalloons";


}

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "master-template";
    }




}
