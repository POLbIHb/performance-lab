
public class Main {
    public static void main(String[] args) {

        //int n = 3;
        //int m = 2;
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int count = 1;

        do {
            System.out.print(count);
            count = (count + m - 2) % n + 1;
        } while (count != 1);
    }

}
