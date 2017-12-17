package othertests;

public class MainApp {

    public static void main(String[] args) {
        String str1 = new String("hello");
        String str2 = new String("hello");
        System.out.println(str1 == str2); //false
        System.out.println(str1.equals(str2)); // true
        System.out.println(str1.equals(str1)); // true
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
