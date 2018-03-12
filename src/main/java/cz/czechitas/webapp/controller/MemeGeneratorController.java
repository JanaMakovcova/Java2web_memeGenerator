package cz.czechitas.webapp.controller;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class MemeGeneratorController {
    @RequestMapping("/meme.html")
       public ModelAndView zobrazMeme() {
        ModelAndView obrazekAVyrok = new ModelAndView("meme-template");
        Random generatorNahodnychCisel = new Random();
        int nahodneCisloObrazek = generatorNahodnychCisel.nextInt(10) + 1 ;
        int nahodneCisloVyrok = generatorNahodnychCisel.nextInt(10) + 1;
        obrazekAVyrok.addObject("obrazekNahodny", nahodneCisloObrazek);
        obrazekAVyrok.addObject("vyrokNahodny", nahodneCisloVyrok);
        return obrazekAVyrok;
    }
}
