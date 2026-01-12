package com.order.orderproject;

import com.order.orderproject.model.Order;
import com.order.orderproject.model.OrderItem;
import com.order.orderproject.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

@Configuration // Indique à Spring que c'est une classe de config
public class DataInitializer {

    @Bean // Spring va exécuter cette méthode au démarrage
    CommandLineRunner initData(OrderRepository orderRepository) {
        return args -> {
            // 1. On vide la base pour ne pas avoir de doublons à chaque redémarrage
            orderRepository.deleteAll();

            System.out.println("--- Démarrage de l'insertion des données de test ---");

            // --- Commande 1 : Electronique ---
            Order o1 = new Order();
            o1.setCustomerName("Alice");
            o1.setStatus("CONFIRMED");
            o1.setCreatedDate(Instant.now());
            // Attention : on remplit la liste items car c'est elle que l'agrégation regarde
            List<OrderItem> items1 = new ArrayList<>();
            items1.add(new OrderItem("Laptop", "ELECTRONICS", 1000, 1));
            items1.add(new OrderItem("Mouse", "ELECTRONICS", 50, 2));
            o1.setItems(items1);

            // --- Commande 2 : Livres ---
            Order o2 = new Order();
            o2.setCustomerName("Bob");
            o2.setStatus("CONFIRMED");
            o2.setCreatedDate(Instant.now());
            List<OrderItem> items2 = new ArrayList<>();
            items2.add(new OrderItem("Java Book", "BOOKS", 40, 1));
            items2.add(new OrderItem("Spring Boot Book", "BOOKS", 50, 1));
            o2.setItems(items2);

            // --- Commande 3 : Mixte (Pour tester si le grouping marche bien) ---
            Order o3 = new Order();
            o3.setCustomerName("Charlie");
            o3.setStatus("CONFIRMED");
            List<OrderItem> items3 = new ArrayList<>();
            items3.add(new OrderItem("Kindle", "ELECTRONICS", 100, 1)); // Doit s'ajouter au total ELECTRONICS
            items3.add(new OrderItem("Novel", "BOOKS", 10, 1));         // Doit s'ajouter au total BOOKS
            o3.setItems(items3);

            // --- Commande 4 : En attente (Doit être ignorée par le match) ---
            Order o4 = new Order();
            o4.setStatus("PENDING");
            o4.setItems(List.of(new OrderItem("TV", "ELECTRONICS", 2000, 1)));

            // Sauvegarde en base
            orderRepository.saveAll(List.of(o1, o2, o3, o4));

            System.out.println("--- Données insérées avec succès ! ---");
        };
    }
}
