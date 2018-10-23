package algojava;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //System.out.println("Hello World!");

        BST<Integer, Integer> test = new BST<Integer, Integer> (2, -5, 0);

        //заполнение дерева
        double a;
        for (int i = -5; i<10; ++i) {
            a = Math.pow(-1, i);
            a *= i;
            test.put((int) a, 3*i);
        }

        //test.draw(test.getN(2));
        test.print(test.getN(2));
        System.out.println("");

        ArrayList<BST<Integer, Integer>.Node> y = new ArrayList<BST<Integer, Integer>.Node> ();

        test.search(-4,7, y);
        //test.print(test.getN(2));

        for(int i = 0; i< y.size(); i++){
            System.out.print(y.get(i).getK());
            System.out.print(" ");
        }
        System.out.println("");
    }
}
