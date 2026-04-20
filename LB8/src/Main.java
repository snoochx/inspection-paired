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
        this.discountPercent = discountPercent;
    }

    public double calculateSubtotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.price * item.quantity;
        }
        return total;
    }

    public double calculateTotal() {
        double subtotal = calculateSubtotal();

        if (discountPercent > 0) {
            subtotal = subtotal - subtotal * discountPercent / 100;
        }

        double tax = subtotal * 0.2;
        double shipping = 0;

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
        System.out.print("Add extra item? (y/n): ");
        String answer = scanner.nextLine();

        if (answer.equals("y")) {
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            calculator.addItem(new OrderItem(name, quantity, price));
        }

        calculator.printReceipt();
    }
}

class OrderItem {
    String name;
    int quantity;
    double price;

    OrderItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}