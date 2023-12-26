package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/")
    public String index() {
        return  "index";
    }

    @GetMapping("/club")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute( "clubs", clubs );
        return "clubs-list";
    }

    @GetMapping("/club/new")
    public  String CreateClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

//    delete request controller, the delete endpoint
    @GetMapping("/club/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId) {
       clubService.delete(clubId);
       return "redirect:/club";
    }

    @GetMapping("/club/search")
    public  String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @PostMapping("/club/new")
    public String saveClub(@ModelAttribute("club") Club club) {
        clubService.saveClub(club);
        return "redirect:/club";
    }

//    need a get endpoint to fetch back data for update
    @GetMapping("/club/{clubId}/edit")
    public String editClubForm (@PathVariable("clubId") long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "club-edits";
    }

//    Edit controller that handles the post request of an edit function
    @PostMapping("/club/{clubId}/edit")
    public  String updateClub(@PathVariable("clubId") long clubId,
                              @Valid @ModelAttribute("club") ClubDto club,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "club-edits";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/club";
    }

//    listing, displaying more details of a specific club
    @GetMapping("/club/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "club-detail";
    }
}
