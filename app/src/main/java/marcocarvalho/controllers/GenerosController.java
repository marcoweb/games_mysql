package marcocarvalho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import marcocarvalho.models.Genero;
import marcocarvalho.repositories.GeneroRepository;

@Controller
@RequestMapping("/generos")
public class GenerosController {
    @Autowired
    private GeneroRepository generosRepo;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("generos", generosRepo.findAll());
        return "/generos/list";
    }

    @RequestMapping("insert")
    public String insert() {
        return "/generos/insert";
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Genero genero = new Genero();
        genero.setNome(nome);
        generosRepo.save(genero);
        return "redirect:/generos/list";
    }
}

