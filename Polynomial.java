public class Polynomial {
    double[] coeffArray;
    //ii. It has a no-argument constructor that sets the polynomial to zero (i.e. the
    //corresponding array would be [0])
    public Polynomial() {
        this.coeffArray = new double[]{0};
    }
    //It has a constructor that takes an array of double as an argument and sets the
    //coefficients accordingly
    public Polynomial(double[] array){
        coeffArray = new double[array.length];
        for(int i = 0; i < (coeffArray.length); i++){
            coeffArray[i] = array[i];
        }
    }
    //It has a method named add that takes one argument of type Polynomial and
    //returns the polynomial resulting from adding the calling object and the argument
    public Polynomial add(Polynomial array2){
        int length = Math.max(coeffArray.length, array2.coeffArray.length);
        double[] newArray = new double[length];
        for(int i = 0; i < length; i++){
            double a = 0;
            double b = 0;
            if (i < coeffArray.length){
                a = coeffArray[i];
            }
            if (i < array2.coeffArray.length){
                b = array2.coeffArray[i];
            }
            double s = a + b;
            newArray[i] = s;
        }
        //
        return new Polynomial(newArray);
    }
    //It has a method named evaluate that takes one argument of type double
    //representing a value of x and evaluates the polynomial accordingly. For example,
    //if the polynomial is 6 − 2𝑥 + 5𝑥% and evaluate(-1) is invoked, the result should
    //be 3.
    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < coeffArray.length; i++){
            //i is the degree of poly.
            //coeffArray[i] is the coefficient.
            if (coeffArray[i] != 0){
                result += coeffArray[i]*(Math.pow(x, i));
            }
        }
        return result;
    }
    //It has a method named hasRoot that takes one argument of type double and
    //determines whether this value is a root of the polynomial or not. Note that a root
    //is a value of x for which the polynomial evaluates to zero.
    public boolean hasRoot(double x){
        return (evaluate(x)==0);
    }

}
