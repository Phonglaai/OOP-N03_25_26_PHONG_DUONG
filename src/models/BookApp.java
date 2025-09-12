package models;
public class BookApp {
    public static void main(String[] args) {
        Book book1 = new Book("Dac Nhan Tam", "Dale Carnegie", 86.000);
        Book book2 = new Book("Cay cam ngot cua toi", "Jose Mauro de Vasconcelos", 75.000);

        System.out.println("Book 1: " + book1.title + " by " + book1.author + " - đ" + book1.price);
        System.out.println("Book 2: " + book2.title + " by " + book2.author + " - đ" + book2.price);
    }
}
