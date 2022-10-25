package marcocarvalho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("update/{id}")
    public String update(Model model, @PathVariable int id) {
        Optional<Genero> genero = generosRepo.findById(id);
        model.addAttribute("genero", genero.get());
        return "/generos/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String saveUpdate(
        @RequestParam("nome") String nome,
        @RequestParam("id") int id) {
            Optional<Genero> genero = generosRepo.findById(id);
            genero.get().setNome(nome);
            generosRepo.save(genero.get());
            return "redirect:/generos/list";
    }

    @RequestMapping("delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Optional<Genero> genero = generosRepo.findById(id);
        model.addAttribute("genero", genero.get());
        return "/generos/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        generosRepo.deleteById(id);
        return "redirect:/generos/list";
    }
}

