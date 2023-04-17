import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main { //Объявление класса Main

    public static void main(String[] args) { //Условие точки входа в программу
        LinkedList linkedList = new LinkedList(); //Объявление создания списка

        // чтение данных из файла и добавление в список
        try {
            File file = new File("D:/Soft/IntelliJ IDEA Community Edition 2022.3.3/projects/ASDC Lab 3/src/products.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // чтение строки из файла
                String[] fields = line.split(","); //Наш разделитель это запятая
                int id = Integer.parseInt(fields[0]); //Здесь я достаю ID (Например 1)
                String name = fields[1]; //Здесь я достаю название (Например Hoodie)
                double price = Double.parseDouble(fields[2]); //Цена
                String brand = fields[3]; //Название бренда
                int quantity = Integer.parseInt(fields[4]); //Количество товара
                Product product = new Product(id, name, price, brand, quantity);
                linkedList.add(product);         // добавление нового продукта в список
            }
        } catch (FileNotFoundException e) {     // обработка исключения, если файл не найден
            e.printStackTrace();
        }

        // поиск элемента по ID
        Node node = linkedList.find(3);

        // удаление элемента по ID
        linkedList.delete(50);

        // вставка нового элемента
        Product newProduct = new Product(50, "Sweater", 29.99, "Adidas", 5);
        linkedList.add(newProduct);

        // вывод обновленного списка на экран
        linkedList.bypassandwithdrawl();
    }
}

class Product {
    public int id;             // уникальный идентификатор продукта
    public String name;        // название продукта
    public double price;       // цена продукта
    public String brand;       // бренд продукта
    public int quantity;       // количество продукта

    public Product(int id, String name, double price, String brand, int quantity) {
        this.id = id;          // присваиваем значение id объекта класса Product
        this.name = name;      // присваиваем значение name объекта класса Product
        this.price = price;    // присваиваем значение price объекта класса Product
        this.brand = brand;    // присваиваем значение brand объекта класса Product
        this.quantity = quantity; // присваиваем значение quantity объекта класса Product
    }
}


class Node {
    public Product product;   // объект класса Product, который будет храниться в узле
    public Node next;         // ссылка на следующий узел списка

    public Node(Product product) {
        this.product = product; // присваиваем значение объекта класса Product
        this.next = null;       // ссылка на следующий узел пока неизвестна, поэтому ей присваивается значение null
    }
}


class LinkedList { // Создание класса LinkedList
    public Node head; // Создание публичной переменной "head", которая будет указывать на голову списка

    public LinkedList() { // Создание конструктора LinkedList
        this.head = null; // Инициализация переменной "head" как null
    }

    public void add(Product product) { // Объявляю метод add
        Node newNode = new Node(product); // создаем новый узел списка
        if (head == null) { // если голова списка пустая
            head = newNode; // то новый узел становится головой списка
        } else { // иначе
            Node current = head; // создаем переменную для текущего узла, которая начинается с головы списка
            while (current.next != null) { // пока следующий узел не равен null
                current = current.next; // перемещаемся на следующий узел
            }
            current.next = newNode; // когда мы доходим до последнего узла, добавляем новый узел
        }
    }


    public void bypassandwithdrawl() { //Объявляю метод обхода и выхода
        Node current = head; // Объявляем переменную для текущего узла, объявляю её началом списка
        while (current != null) { // Пока текущий узел не является пустым
            // Вывожу информацию о продукте на экран
            System.out.println(current.product.id + "," + current.product.name + "," + current.product.price + "," + current.product.brand + "," + current.product.quantity);
            current = current.next; // Переходим к следующему узлу списка
        }
    }

    public void delete(int id) {
        Node current = head;  // текущий узел устанавливается на начало списка
        Node previous = null;  // предыдущий узел инициализируется как null
        while (current != null) {  // проход по всем узлам списка до его конца
            if (current.product.id == id) {  // если ID текущего узла соответствует заданному ID
                if (previous == null) {  // если предыдущий узел не задан (т.е. это головной узел)
                    head = current.next;  // головной узел переназначается на следующий за текущим
                } else {
                    previous.next = current.next;  // связь между предыдущим и текущим узлом обходит текущий узел
                }
                return;  // возврат из метода после удаления узла
            }
            previous = current;  // текущий узел сохраняется как предыдущий узел
            current = current.next;  // текущий узел переназначается на следующий за ним узел
        }
    }


    public Node find(int id) { //Объявление метода find
        Node current = head; // Инициализация переменной "current" ссылкой на головной узел списка "head".
        while (current != null) { // Пока "current" не указывает на конец списка (null), выполнять цикл.
            if (current.product.id == id) { // Если идентификатор продукта в текущем узле равен переданному идентификатору, выполнить действия внутри блока.
                return current; // Вернуть ссылку на текущий узел, так как искомый узел найден.
            }
            current = current.next; // Обновить переменную "current", ссылкой на следующий узел списка.
        }
        return null; // Если искомый узел не найден в списке, вернуть значение "null".
    }
}
