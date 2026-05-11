package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// MVC controller: a termek lista, kereses, felvetel es torles webes utvonalait kezeli.
@Controller
public class ProductController {
    // A controller a service reteget hasznalja, igy nem kozvetlenul az adatbazissal dolgozik.
    private final ProductService service;

    // A Spring itt adja at a ProductService peldanyt.
    public ProductController(ProductService service) {
        this.service = service;
    }

    // Fooldal: ha van keresoszo, szurt listat ad, kulonben az osszes termeket megjeleniti.
    @GetMapping("/")
    public String viewHomePage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        // A null es a csak szokozbol allo kereseseket ures keresokent kezeljuk.
        String trimmedKeyword = keyword == null ? "" : keyword.trim();
        boolean hasKeyword = !trimmedKeyword.isEmpty();
        // A template a "products" es "keyword" model adatokbol rajzolja ki a tablazatot es a keresot.
        model.addAttribute("products", hasKeyword ? service.searchProductsByName(trimmedKeyword) : service.getAllProducts());
        model.addAttribute("keyword", trimmedKeyword);
        return "index";
    }

    // Megnyitja az uj termek urlapot egy ures Product objektummal.
    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    // Feldolgozza az urlapon bekuldott termeket.
    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        // Ha a validacios annotaciok hibat talalnak, visszaterunk az urlapra es megjelennek a hibak.
        if (result.hasErrors()) {
            return "new_product";
        }
        // Hibatlan adat eseten mentunk, majd visszairanyitunk a fooldalra.
        service.saveProduct(product);
        return "redirect:/";
    }

    // Torli a megadott id-ju termeket, majd friss listat mutat.
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        service.deleteProduct(id);
        return "redirect:/";
    }
}
