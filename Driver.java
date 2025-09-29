import java.io.*;
public class Driver {
    public static void main(String [] args) throws IOException{
        double[] c1 = {6,3,0,5};
        Polynomial p1 = new Polynomial(c1);
        double[] c2 = {0,-2,0,0,-9};
        Polynomial p2 = new Polynomial(c2);
        Polynomial s = p1.add(p2);
        System.out.println("Result Polynomial:");
        for (int i = 0; i < s.coeffArray.length; i++) {
            System.out.println("Coefficient: " + s.coeffArray[i] +
                    ", Exponent: " + s.expArray[i]);
        }
        System.out.println("=====evaluate test=====");
        System.out.println("6+3x+5x3");// 6+9+5*27=150
        System.out.println(p1.evaluate(3));
//===========================================
        File file = new File("/Users/zhangxiaoran/Desktop/j_code/0/src/ab.txt");

        // d
        Polynomial p = new Polynomial(file);
        System.out.println("Polynomial terms from ab.txt:");
        for (int i = 0; i < p.coeffArray.length; i++) {
            System.out.println("Coefficient = " + p.coeffArray[i] +
                    ", Exponent = " + p.expArray[i]);
    }
        //e
        String outputFileName = "/Users/zhangxiaoran/Desktop/j_code/0/src/output.txt";
        p.saveToFile(outputFileName);
        BufferedReader b = new BufferedReader(new FileReader(outputFileName));
        String savedLine = b.readLine();
        b.close();
        System.out.println("Content of saved file:");
        System.out.println(savedLine);

    }}
