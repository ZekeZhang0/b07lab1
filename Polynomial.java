import java.util.*;
import java.io.*;


public class Polynomial {
    double[] coeffArray;
    int[] expArray;

    //a. Replace the array representing the coefficients by two arrays: one representing the
    // non-zero coefficients (of type double) and another one representing the corresponding
    //exponents (of type int). For example, the polynomial 6 − 2𝑥 + 5𝑥3 would be represented
    //using the arrays [6, -2, 5] and [0, 1, 3]
    public Polynomial() {
        this.expArray = new int[]{0};
        this.coeffArray = new double[]{0};
    }

    //It has a constructor that takes an array of double as an argument and sets the
    //coefficients accordingly
    public Polynomial(double[] array){
        int length = array.length;
        int count = 0;
        for (int i = 0; i < length; i++){
            if (array[i] != 0){
                count += 1;
            }
        }
        coeffArray = new double[count];
        expArray = new int[count];

        int j = 0;
        for (int i = 0; i < length; i++){
            if(array[i] != 0){
                coeffArray[j] = array[i];
                expArray[j] = i;
                j++;
            }
        }
        //
    }
    //It has a method named add that takes one argument of type Polynomial and
    //returns the polynomial resulting from adding the calling object and the argument
    //help func: Remove the terms whose coefficients are zero.
    private Polynomial RemoveZero(double[] coeff, int[] exp, int length){
        int count = 0;
        for(int i = 0; i < length; i++){
           if (coeff[i] != 0){
               count++;
           }
        }
        double newCoeff[] = new double[count];
        int newExp[] = new int[count];

        int index = 0;
        for (int j = 0; j<length; j++){
            if(coeff[j] != 0){
                newCoeff[index] = coeff[j];
                newExp[index] = exp[j];
                index++;
            }
        }
        //================ =-)
        Polynomial result = new Polynomial();
        result.coeffArray = newCoeff;
        result.expArray = newExp;
        return  result;
    }
//-------------------------------
    public Polynomial add(Polynomial array2){
        //return new Polynomial(newArray);
        int longestLength = this.coeffArray.length + array2.coeffArray.length;
        double[] tempCoeff = new double[longestLength];
        int[] tempExp = new int[longestLength];

        int index = 0;

        // input this
        for (int i = 0; i < this.coeffArray.length; i++){
            tempCoeff[index] = this.coeffArray[i];
            tempExp[index] = this.expArray[i];
            index++;
        }
        //input array2
        //unordered is OK!!!
        for (int i = 0; i <array2.expArray.length; i++){
            double coeff = array2.coeffArray[i];
            int exp = array2.expArray[i];

            boolean found = false;
            for (int j = 0; j < index; j++){
                if (tempExp[j] == exp){
                    tempCoeff[j]+=coeff;
                    found = true;
                    break;
                }
            }
            //
            if (!found){
                tempCoeff[index] = coeff;
                tempExp[index] = exp;
                index+=1;
            }
        }
        //========:-)
        return RemoveZero(tempCoeff, tempExp, index);
    }

    //It has a method named evaluate that takes one argument of type double
    //representing a value of x and evaluates the polynomial accordingly. For example,
    //if the polynomial is 6 − 2𝑥 + 5𝑥% and evaluate(-1) is invoked, the result should
    //be 3.
    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < coeffArray.length; i++){
            result += coeffArray[i] * Math.pow(x, expArray[i]);
        }
        return result;
    }
    //It has a method named hasRoot that takes one argument of type double and
    //determines whether this value is a root of the polynomial or not. Note that a root
    //is a value of x for which the polynomial evaluates to zero.
    public boolean hasRoot(double x){
        return (evaluate(x)==0);
    }
    //c. Add a method named multiply that takes one argument of type Polynomial and returns
    //the polynomial resulting from multiplying the calling object and the argument. The
    //resulting polynomial should not contain redundant exponents.
    //help function:
    private void combineSameTerms(double[] coeff, int[] exp, int length){
        for (int i = 0; i < length; i++){
            for(int j = i+1; j < length; j++){
                if (exp[i] == exp[j]){
                    coeff[i]+=coeff[j];
                    coeff[j] = 0;
                }
            }
        }
    }
    //--------------
    public Polynomial multiply(Polynomial array2){
        int longestLength = (this.coeffArray.length) * (array2.coeffArray.length);
        double[] tempCoeff = new double[longestLength];
        int[] tempExp = new int[longestLength];

        int index = 0;

        for (int i = 0; i<this.coeffArray.length; i++){
            for (int j = 0; j<array2.coeffArray.length; j++){
                tempCoeff[index] = this.coeffArray[i] * array2.coeffArray[j];
                tempExp[index] = this.expArray[i] + array2.expArray[j];
                index++;
            }
        }
        //===
        combineSameTerms(tempCoeff, tempExp, index);
        return RemoveZero(tempCoeff, tempExp, index);
    }

    public Polynomial(File file) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader(file));
        String line = b.readLine();
        b.close();
        //append + before -
        StringBuilder tempLine = new StringBuilder();
        for (int i = 0; i < line.length(); i++){
            char c = line.charAt(i);

            if (c == '-' && i != 0){
                tempLine.append('+');
            }
            tempLine.append(c);
        }
        String newLine = tempLine.toString();
        String[] terms = newLine.split("\\+");
        //
        double[] coeff = new double[terms.length];
        int[] exp = new int[terms.length];

        for(int j = 0; j < terms.length; j++){
            String term = terms[j];
            if(term.isEmpty()){
                continue;
            }
            // constant
            if (!term.contains("x")){
                coeff[j] = Double.parseDouble(term);
                exp[j] = 0;
            }
            else{
                String[] termParts = term.split("x");
                //coeff 1
                if (termParts[0].equals("")){
                    coeff[j] = 1;
                }
                //-1
                else if (termParts[0].equals("-")) {
                    coeff[j] = -1;
                }
                //other
                else {coeff[j] = Double.parseDouble(termParts[0]);}
                //======exp
                if (termParts.length == 1 || termParts[1].equals("")){
                    exp[j] = 1;
                }
                else{exp[j] = Integer.parseInt(termParts[1]);}
            }
        }
        Polynomial p = RemoveZero(coeff, exp, terms.length);
        this.coeffArray = p.coeffArray;
        this.expArray = p.expArray;
    }
    //e. Add a method named saveToFile that takes one argument of type String representing a
    //file name and saves the polynomial in textual format in the corresponding file (similar to
    //the format used in part d)
    public void saveToFile(String fileName) throws IOException{
        BufferedWriter b = new BufferedWriter(new FileWriter(fileName));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i< this.coeffArray.length; i++){
            double x = this.coeffArray[i];
            int e = this.expArray[i];
            if (x==0){
                continue;
            }
            if (i>=1 && x>0){
                s.append("+");
            }
            //for x
            if (e != 0){
                if(x == 1){
                    //void
                }
                else if(x == -1){
                    s.append("-");
                }
                else{s.append(x);}
            }
            else{s.append(x);}

            //for e
            if(e >= 1){
                s.append("x");
                if(e != 1){
                    s.append(e);
                }
            }
        }

//
        b.write(s.toString());
        b.close();
    }
 //===========the END===========
}
