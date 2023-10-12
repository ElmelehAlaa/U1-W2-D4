import ClassiEs.Customer;
import ClassiEs.Ordine;
import ClassiEs.Prodotti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Prodotti prodotto1 = new Prodotti(1L, "libro1", "Books", 75.0);
        Prodotti prodotto2 = new Prodotti(2L, "libro2", "Books", 110.0);
        Prodotti prodotto3 = new Prodotti(3L, "libro3", "Sasso", 500.0);
        Prodotti prodotto4 = new Prodotti(4L, "libro4", "Baby", 300.0);

        ///////////////////////// creo  una lista di prodotti customer e ordini//////////////////////////////////////
        List<Prodotti> productList = new ArrayList<>();
        productList.add(prodotto1);
        productList.add(prodotto2);
        productList.add(prodotto3);
        productList.add(prodotto4);
        Customer customer1 = new Customer(1L, "Ajeje", 1);
        Customer customer2 = new Customer(2L, "giacomo", 2);

        List<Ordine> orderList = new ArrayList<>();
        orderList.add(new Ordine(1L, "In progress", LocalDate.now(), LocalDate.now().plusDays(2), List.of(prodotto1, prodotto3), customer1));
        orderList.add(new Ordine(2L, "Delivered", LocalDate.now(), LocalDate.now().plusDays(5), List.of(prodotto2), customer2));
        orderList.add(new Ordine(3L, "Shipped", LocalDate.now(), LocalDate.now().plusDays(3), List.of(prodotto1), customer1));

        //////////////// filtro ES.1//////////////////////////////
        List<Prodotti> booksUnder100 = productList.stream()
                .filter(product -> product.getCategory().equals("Books") && product.getPrice() < 100)
                .toList();

        List<Prodotti>booksBaby= productList.stream() .filter(product ->product.getCategory().equals("Baby")).toList();
        Map<Customer, List<Ordine>> ordiniPerCliente = orderList.stream()
                .collect(Collectors.groupingBy(Ordine::getCustomer));

        ///////////////// Stampo i prodotti trovati///////////////////
        for (Prodotti product : booksUnder100) {
            System.out.println("ID: " + product.getId() + ", Nome: " + product.getName() + ", Categoria: " + product.getCategory() + ", Prezzo: " + product.getPrice());
        }
        for(Prodotti product:booksBaby){
            System.out.println("ID: " + product.getId() + ", Nome: " + product.getName() + ", Categoria: " + product.getCategory() + ", Prezzo: " + product.getPrice());

        }
        for (Map.Entry<Customer, List<Ordine>> entry : ordiniPerCliente.entrySet()) {
            Customer customer = entry.getKey();
            List<Ordine> ordini = entry.getValue();
            System.out.println("Cliente: " + customer.getName());
            System.out.println("Ordini:");
            for (Ordine order : ordini) {
                System.out.println("ID: " + order.getId() + ",Stato:" + order.getStatus());
            }
            System.out.println();
        }
    }
}