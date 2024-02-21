package com.example.myfirsteshop.controller;

import com.example.myfirsteshop.model.Brand;
import com.example.myfirsteshop.model.ProdType;
import com.example.myfirsteshop.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ShopController {

    private static final List<Product> PRODUCT_LIST = new ArrayList<>();
    static {
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.MARSHALL, "Marshall MS-4", "Table-top Marshall amp", 890, 8, "images/marshall-ms.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.GIBSON, "Gibson LP Studio 2018 Smokehouse Burst", "Classic LP in lightweight version!", 38_400,3, "images/gibson-lp.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.BOSS, "Boss DD-500", "Collection of beautiful delay sounds", 11_200, 7, "images/boss-dd500.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.HUGHESKETTNER, "H&K GrandMeister Deluxe 40", "Extreme versatile guitar amplifier!", 31_580,5, "images/hugheskettner-grandmeister40.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.MESA, "Mesa Boogie Tripple Rectifier", "Classic RECTO sound!", 82_700, 1, "images/mesa-tripplerectifier.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.LTD, "LTD EC-401 SW", "Snow white LP style guitar", 23_390,2, "images/ltd-401.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.CABINET, Brand.ENGL, "ENGL 4X12 Pro", "Brutal sound - brutal build quality", 25_000,2, "images/engl-cabinet.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.BOSS, "Boss VB-2W", "Waza Craft series vibrato pedal", 5_990, 0, "images/boss-vb2w.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.JACKSON, "Jackson Pro-KV FR", "Flying V in Ferrari Red", 24_800,2, "images/jackson-proKV.png"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.BOSS, "Boss NS-2", "Expander - Noise Gate - classic model", 2_590, 7, "images/boss-ns2.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.MARSHALL, "Marshall DSL20HR", "Lunchbox 20W amplifier", 10_490, 8, "images/marshall-dsl.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.CABINET, Brand.MESA, "Mesa Boogie 4X12 Vintage 30", "Favored sound of both many guitar players and sound engineers", 42_790,3, "images/mesa-cabinet.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.BOSS, "Boss Katana HEAD", "BOSS top class modeling amp", 10_890, 4, "images/boss-katana.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.MAXON, "Maxon OD-808", "Legendary TubeScreamer predecessor", 2_800,6, "images/maxon-od808.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.GIBSON, "Gibson LP Custom 1957 Ebony", "Boutique electric guitar", 159_990,1, "images/gibson-custom.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.BOSS, "Boss DS-1", "DS-1 is known for its low price", 1_700, 0, "images/boss-ds1.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.PEAVEY, "Peavey 6505MH", "Revision of a classic 5150 in lunchbox format", 15_990,8, "images/peavey-6505mh.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.BOSS, "Boss SD-2W", "Waza Craft series overdrive pedal", 3_780, 1, "images/boss-sd2w.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.JACKSON, "Jackson JS-32 Dinky", "Black double cut guitar", 7_490,3, "images/jackson-dinky.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.BOSS, "Boss DM-2W", "Waza Craft series delay pedal", 4_490, 0, "images/boss-dm2w.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.MARSHALL, "Marshall 2002 JCM 900", "Boutique Marshall amp", 25_990, 0, "images/marshall-jcm.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.MAXON, "MAXON FA-10", "Maxon made fuzz pedal", 3_562, 4, "images/maxon-fuzz.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.GIBSON, "Gibson Slash", "Slash signature LP", 72_440,1, "images/gibson-slash.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.CABINET, Brand.MARSHALL, "Marshall 1960A", "Classic Marshall sounds", 15_980,2, "images/marshall-1960.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.PEDAL, Brand.BOSS, "Boss RV-500", "HQ Reverb sounds for gig, home and studio", 10_790, 0, "images/boss-rv500.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.JACKSON, "Jackson Soloist", "Snow white double cut guitar", 14_840,2, "images/jackson-soloist.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.AMP, Brand.ENGL, "Engl Ironball", "Lunchbox 30W amplifier", 18_260, 1, "images/engl-ironball.jpg"));
        PRODUCT_LIST.add(new Product(ProdType.GUITAR, Brand.LTD, "LTD EC-256 SW", "Snow white LP style guitar", 12_390,6, "images/ltd-256.jpg"));
    }

    @GetMapping("/search")
    public String getProducts(Model model,
                              @RequestParam("brand") Optional<Brand> brand,
                              @RequestParam("type") Optional<ProdType> type,
                              @RequestParam("name") Optional<String> name,
                              @RequestParam("min") Optional<Double> min,
                              @RequestParam("max") Optional<Double> max,
                              @RequestParam("ascending") Optional<Boolean> ascending,
                              @RequestParam("available") Optional<Boolean> availableOnly) {

        model.addAttribute("searchPool", sort(filter(brand, type, name, min, max, availableOnly), ascending));
        model.addAttribute("types", ProdType.getTypes());
        model.addAttribute("brands", Brand.getBrands());
        return "searchResults";
    }

    private List<Product> filter(Optional<Brand> brand,
                                 Optional<ProdType> type,
                                 Optional<String> name,
                                 Optional<Double> min,
                                 Optional<Double> max,
                                 Optional<Boolean> showOnlyAvailable) {

        return PRODUCT_LIST.stream()
                .filter(p -> (brand.isPresent() && p.getBrand().equals(brand.get())) || brand.isEmpty())
                .filter(p -> (type.isPresent() && p.getType().equals(type.get())) || type.isEmpty())
                .filter(p -> (name.isPresent() && p.getName().contains(name.get())) || name.isEmpty())
                .filter(p -> (min.isPresent() && p.getPrice() >= min.get()) || min.isEmpty())
                .filter(p -> (max.isPresent() && p.getPrice() <= max.get()) || max.isEmpty())
                .filter(p -> (showOnlyAvailable.isPresent() && (p.getQuantity() > 0 || !showOnlyAvailable.get())) || showOnlyAvailable.isEmpty())
                .collect(Collectors.toList());
    }

    private List<Product> sort(List<Product> productList, Optional<Boolean> isAscending) {
        if (isAscending.isEmpty()) { return productList; }
        Collections.sort(productList);
        if (!isAscending.get()) {
            Collections.reverse(productList);
        }
        return productList;
    }
}
