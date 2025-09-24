import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники", accessories);

        List<Product> products = Arrays.asList(product1, product2, product3);

        Cart cart = new Cart();
        List<Order> orders = new ArrayList<>();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Оформити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів за назвою");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> products.forEach(System.out::println);
                case 2 -> {
                    System.out.print("Введіть ID товару: ");
                    int addId = scanner.nextInt();
                    scanner.nextLine();
                    products.stream()
                            .filter(p -> p.getId() == addId)
                            .findFirst()
                            .ifPresentOrElse(cart::addProduct,
                                    () -> System.out.println("Товар не знайдено."));
                }
                case 3 -> {
                    System.out.print("Введіть ID товару для видалення: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    products.stream()
                            .filter(p -> p.getId() == removeId)
                            .findFirst()
                            .ifPresentOrElse(cart::removeProduct,
                                    () -> System.out.println("Товар не знайдено."));
                }
                case 4 -> System.out.println(cart);
                case 5 -> {
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній!");
                    } else {
                        Order order = new Order(cart);
                        orders.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                }
                case 6 -> {
                    if (orders.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        orders.forEach(System.out::println);
                    }
                }
                case 7 -> {
                    System.out.print("Введіть назву товару для пошуку: ");
                    String query = scanner.nextLine().toLowerCase();
                    products.stream()
                            .filter(p -> p.getName().toLowerCase().contains(query))
                            .forEach(System.out::println);
                }
                case 0 -> {
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                }
                default -> System.out.println("Невідома опція.");
            }
        }
    }
}
