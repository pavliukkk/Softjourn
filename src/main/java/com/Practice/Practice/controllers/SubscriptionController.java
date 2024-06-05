package com.Practice.Practice.controllers;

import com.Practice.Practice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscriptionController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam("email") String email) {

        emailService.sendEmail(email, "FoodZero Recipes", "INGREDIENTS for 1 portion Basic:\\n• Beef TM Organic Meat.100 g\\n• garlic ½ pc.\\n• Bulgarian red pepper ½ pc.\\n• chicken broth 1 st. l\\n• soy sauce ¾ pc.\\n• sugar brown ¼ tsp.\\n• corn starch ½ tsp.\\n• sesame oil 1 tsp.Mustard Sauce:\\n• mustard Dijon ½ st. l\\n• water 1 tbsp l\\n• brown sugar ½ tsp.\\n• Lemon juice 1 tsp.\\nSTEP 1\\nMix all the ingredients for mustard sauce. Put aside Beef is cut across the fibers. Slender meat strips mixed with marinade. Set aside for 20 minutes.\\nSTEP 2\\nHeat the oil in a saucepan on medium heat. Quickly fry the garlic. Add strips of red pepper. Stir until smooth, add chicken broth. Cook for about 5 minutes, add salt, put it in a saucepan. Add oil to the stew. Put beef with marinade in one layer without overlapping pieces of each other. Fry until the color changes. Turn to the other side. Add from a bowl of fried pepper and mustard sauce. Mix quickly and place on a serving dish.\\nSTEP 3\\nGarnish sesame seeds and green onions at will. Serve immediately after cooking.',\n" +
                "                    'foodzero.restaurant@gmail.com");

        return "redirect:/";
    }
}
