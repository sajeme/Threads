package Modelo;

public class Modelo {

    private String[] cadenaEntrada;
    private String[] pila;
    private char[] estados;
    private String resultado;
    private boolean palindrome;
    private int nTrans;
    private boolean impar;
    private boolean otroCaracter;

    public String[] getCadenaEntrada() {
        return cadenaEntrada;
    }

    public void setCadenaEntrada(String[] cadenaEntrada) {
        impar = false;
        otroCaracter = false;
        this.cadenaEntrada = cadenaEntrada;

        estados = new char[cadenaEntrada.length];
        nTrans = cadenaEntrada.length;

        for (int i = 0; i < cadenaEntrada.length; i++) {
            if (i < (cadenaEntrada.length / 2)) {
                estados[i] = 'S';
            } else {
                estados[i] = 'F';
            }
        }

        //Ejecutar solo si se cumplen las restricciones
        //Primero que sean solo caracteres a y b
        //Despues que sea un numero par
        for(int i=0; i<cadenaEntrada[0].length(); i++){
            if (cadenaEntrada[0].charAt(i) != 'a' && cadenaEntrada[0].charAt(i) != 'b') {
                   otroCaracter = true;
            }
        }
        if (cadenaEntrada[0].length() % 2 != 0) {
            impar = true;
        }
        if(otroCaracter){
            resultado = "Este solo admite los caracteres 'a' y 'b'.";
        }
        else if(impar){
            resultado = "La cadena fue rechazada por que no tiene longitud impar.";
        }
        else if(!otroCaracter && !impar){
            isPalindrome();
        }
        
        
    }

    public String[] getPila() {
        return pila;
    }

    public void setPila(String[] pila) {
        this.pila = pila;
    }

    public String getResultado() {
        return resultado;
    }

    public char[] getEstados() {
        return estados;
    }
    
    public boolean getOtroCaracter(){
        return otroCaracter;
    }
    
    public boolean getImpar(){
        return impar;
    }
    
    private void isPalindrome() {
        palindrome = true;
        resultado = "La cadena ingresada si es un palindromo.";
        int i = 0, j = cadenaEntrada[0].length() - 1;
        while (i < j) {
            if (cadenaEntrada[0].charAt(i) != cadenaEntrada[0].charAt(j)) {
                palindrome = false;
                resultado = "La cadena ingresada no es un palindromo.";
            }

            i++;
            j--;
        }
    }

    public static String removeByIndex(String str, int index) {
        StringBuilder sb = new StringBuilder();
        if (index > 0) {
            sb.append(str, 0, index);
        }
        if (index < str.length() - 1) {
            sb.append(str, index + 1, str.length());
        }
        return sb.toString();
    }

    public boolean getPalindrome() {
        return palindrome;
    }

    public String reverse(String str) {

        StringBuilder input1 = new StringBuilder();

        // append a string into StringBuilder input1
        input1.append(str);

        // reverse StringBuilder input1
        input1.reverse();
        return String.valueOf(input1);
    }

    public void caso1() {
        pila = new String[nTrans];
        pila[0] = "E";
        pila[cadenaEntrada.length - 1] = "E";

        for (int i = 1; i < cadenaEntrada.length - 1; i++) {

            if (i > 0 && i < cadenaEntrada.length) {
                cadenaEntrada[i] = cadenaEntrada[i - 1];
                cadenaEntrada[i] = removeByIndex(cadenaEntrada[i], 0);
            }
            if (i == (cadenaEntrada.length / 2)) {
                cadenaEntrada[i] = cadenaEntrada[i - 1];
            }
        }
        cadenaEntrada[cadenaEntrada.length - 1] = "E";
        pila[1] = String.valueOf(cadenaEntrada[0].charAt(0));
        for (int i = 2; i < cadenaEntrada.length - 2; i++) {
            if (i > 1 && i < (cadenaEntrada.length / 2)) {
                pila[i] = cadenaEntrada[i - 1].charAt(0) + pila[i - 1];
            }
            if (i == (cadenaEntrada.length / 2)) {
                pila[i] = pila[i - 1];
            }
            if (i > (cadenaEntrada.length / 2)) {
                pila[i] = cadenaEntrada[i];
            }
        }
        pila[cadenaEntrada.length - 2] = cadenaEntrada[cadenaEntrada.length - 2];
    }

    public void caso2() {
        //Manejar PorLeer y Pila

        for (int i = 1; i < cadenaEntrada.length - 1; i++) {

            if (i > 0 && i < cadenaEntrada.length) {
                cadenaEntrada[i] = cadenaEntrada[i - 1];
                cadenaEntrada[i] = removeByIndex(cadenaEntrada[i], 0);
            }
            if (i == (cadenaEntrada.length / 2)) {
                cadenaEntrada[i] = cadenaEntrada[i - 1];
            }
        }
        cadenaEntrada[cadenaEntrada.length - 1] = "E";

        pila = new String[nTrans];
        pila[0] = "E";

        pila[1] = String.valueOf(cadenaEntrada[0].charAt(0));
        for (int i = 2; i < cadenaEntrada.length - 2; i++) {
            if (i > 1 && i < (cadenaEntrada.length / 2)) {
                pila[i] = cadenaEntrada[i - 1].charAt(0) + pila[i - 1];
            }
            if (i == (cadenaEntrada.length / 2)) {
                pila[i] = pila[i - 1];
            }
            if (i > (cadenaEntrada.length / 2)) {
                pila[i] = pila[i - 1];
                pila[i] = removeByIndex(pila[i], 0);
            }
        }
        pila[nTrans - 2] = pila[nTrans - 3];
        pila[nTrans - 2] = removeByIndex(pila[nTrans - 2], 0);
        //pila[cadenaEntrada.length - 2] = pila[cadenaEntrada.length - 2];
        pila[cadenaEntrada.length - 1] = pila[cadenaEntrada.length - 2];
    }
}
