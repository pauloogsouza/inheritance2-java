package application;

import model.entities.ImportedProduct;
import model.entities.Product;
import model.entities.UsedProduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Product> list = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int n = sc.nextInt();

        for (int i=1; i<=n; i++) {
            System.out.println("Product " + i + "# data:");
            System.out.print("Common, used, or imported(c/u/i)? ");
            char productType = sc.next().charAt(0);
            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            if (productType == 'c') {
                list.add(new Product(name, price));
            }
            else if (productType == 'u') {
                try {
                    System.out.print("Manufacture date(DD/MM/YYYY): ");
                    sc.nextLine();
                    Date date = sdf.parse(sc.nextLine());
                    list.add(new UsedProduct(name, price, date));
                }
                catch (ParseException e) {
                    System.out.println("Invalid date format!");
                }
            }
            else {
                System.out.print("Customs fee: ");
                double customsFee = sc.nextDouble();
                list.add(new ImportedProduct(name, price, customsFee));
            }
        }

        System.out.println();
        System.out.println("PRICE TAGS:");
        for (Product p : list) {
            System.out.println(p.priceTag());
        }
        
        sc.close();
    }
}
