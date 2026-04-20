import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private List<OrderItem> items = new ArrayList<>();
    private double discountPercent = 0;

    public void addItem(OrderItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void setDiscountPercent(double discountPercent) {
        // Замечание: метод принимает любое значение, включая отрицательное
        // или больше 100. Нужна проверка диапазона.
        this.discountPercent = discountPercent;
    }

    public double calculateSubtotal() {
        double total = 0;
        for (OrderItem item : items) {
            // Замечание: здесь нет защиты от невалидных товаров,
            // например с отрицательной ценой или количеством.
            total += item.price * item.quantity;
        }
        return total;
    }

    public double calculateTotal() {
        double subtotal = calculateSubtotal();

        // Замечание: метод смешивает сразу несколько правил:
        // скидка, налог и доставка. Лучше вынести расчёты в отдельные методы.
        if (discountPercent > 0) {
            subtotal = subtotal - subtotal * discountPercent / 100;
        }

        // Замечание: магическое число 0.2 не объяснено.
        double tax = subtotal * 0.2;
        double shipping = 0;

        // Замечание: магические числа 100, 15 и 5 не вынесены в константы.
        if (subtotal < 100) {
            shipping = 15;
        } else {
            shipping = 5;
        }

        return subtotal + tax + shipping;
    }

    public void printReceipt() {
        System.out.println("=== RECEIPT ===");
        for (OrderItem item : items) {
            // Замечание: здесь тоже доверие данным полностью.
            // Если в item попадут некорректные значения, вывод будет неверным.
            System.out.println(item.name + " x" + item.quantity + " = " + (item.price * item.quantity));
        }
        System.out.println("Subtotal: " + calculateSubtotal());
        System.out.println("Discount: " + discountPercent + "%");
        System.out.println("Total: " + calculateTotal());
    }

    public static void main(String[] args) {
        Main calculator = new Main();
        calculator.addItem(new OrderItem("Keyboard", 1, 3500));
        calculator.addItem(new OrderItem("Mouse", 2, 1200));
        calculator.addItem(new OrderItem("Notebook", 3, 150));
        calculator.setDiscountPercent(10);

        Scanner scanner = new Scanner(System.in);
        // Замечание: Scanner не закрывается.
        // Для небольшого примера это не критично, но в реальном коде это
        // нарушение аккуратности работы с ресурсами.
        System.out.print("Add extra item? (y/n): ");
        String answer = scanner.nextLine();

        // Замечание: сравнение слишком жёсткое.
        // Пользователь может ввести Y, yes или пробелы.
        if (answer.equals("y")) {
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Quantity: ");
            // Замечание: есть риск NumberFormatException при некорректном вводе.
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Price: ");
            // Замечание: здесь такой же риск ошибки ввода.
            double price = Double.parseDouble(scanner.nextLine());

            calculator.addItem(new OrderItem(name, quantity, price));
        }

        calculator.printReceipt();
    }
}

class OrderItem {
    // Замечание: поля не private, поэтому нарушается инкапсуляция.
    // Это удобно для учебного примера, но плохо для поддерживаемого кода.
    String name;
    int quantity;
    double price;

    OrderItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}