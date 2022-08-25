package java_lab01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Java_Lab01 {

    public static void LeerC(Scanner sc, String file_name) { //Mostrar Datos de Clientes.txt
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");
                    System.out.println(temp[0] + "," + temp[1] + "," + temp[2] + "," + temp[3] + "," + temp[4]);

                }
                br.close();
                hay = true;
            } catch (IOException ex) {
                System.out.println("No se encontró archivo");
                file_name = sc.nextLine();
            }

        }
    }

    public static void LeerFac(Scanner sc, String file_name) { //Mostrar datos de Facturas.txt
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");
                    System.out.println(temp[0] + "," + temp[1] + "," + temp[2] + "," + temp[3]);

                }
                br.close();
                hay = true;
            } catch (IOException ex) {
                System.out.println("No se encontró archivo");
                file_name = sc.nextLine();
            }

        }
    }

    public static int sizeF(String file_name) {
        /*
        Argumentos:
        String file_name: Nombre del archivo (.txt)
        
        Regresa:
        int result: Numero de lineas en el archivo
         */
        int result = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
            LineNumberReader count = new LineNumberReader(br);
            while (count.readLine() != null) {

            }

            result = count.getLineNumber();
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;

    }

    public static void sortF(String file_name) {
        String data[] = new String[sizeF(file_name)]; // Arreglo almacena contenido de cada linea
        try {
            BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt")); // Abre archivo
            String line = null;

            int i = 0;
            while ((line = br.readLine()) != null) { // Copia contenido 
                data[i] = line;
                i++;
            }
            if (file_name.equals("Facturas")) {
                bubble2(data); // Sort data to Sort lines
            } else {
                bubble(data);
            }

            br.close(); // Cierra Archivo

            FileWriter outFile = new FileWriter(file_name + ".txt", false); // Reabre en modo reescritura
            PrintWriter register = new PrintWriter(outFile);

            for (int j = 0; j < data.length; j++) { // Escribe cada linea del arreglo ordenado en el archivo
                register.println(data[j]);
            }
            register.close(); // Cierra archivo
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void sw(String[] ls, int i, int j) {
        /*
        Argumentos:
        String[] ls: Arreglo de strings para ordenar
        int i: i-esimo elemento para intercambiar
        int j: j-esimo elemento con el que es intercambiado
        
         */
        String ch = ls[i];
        ls[i] = ls[j];
        ls[j] = ch;
    }

    public static void bubble(String[] ls) { //Algortimo burbuja de ordenamiento
        int pairs = ls.length;
        boolean swapped = true;
        while (swapped) {
            pairs = pairs - 1;
            swapped = false;
            for (int i = 0; i < pairs; i++) {
                int x = Integer.valueOf(ls[i].split("\t")[0]);
                // Entero del primer elemento del split del i/j -esimo elemento del arreglo
                int y = Integer.valueOf(ls[i + 1].split("\t")[0]);
                if (x > y) {
                    sw(ls, i, (i + 1));
                    swapped = true;
                }
            }
        }
    }

    public static void bubble2(String[] ls) {
        //ordena Facturas.txt de forma primaria por cedula y secundaria por CodProducto
        int pairs = ls.length;
        boolean swapped = true;
        while (swapped) {
            pairs = pairs - 1;
            swapped = false;
            for (int i = 0; i < pairs; i++) {
                int x = Integer.valueOf(ls[i].split("\t")[0]);
                int y = Integer.valueOf(ls[i + 1].split("\t")[0]);
                int x2 = Integer.valueOf(ls[i].split("\t")[2]);
                int y2 = Integer.valueOf(ls[i + 1].split("\t")[2]);
                if ((x > y) || ((x == y) && x2 > y2)) {
                    sw(ls, i, (i + 1));
                    swapped = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        sortF("Clientes");
        sortF("Facturas");
        System.out.println("Facturas");
        LeerFac(sc, "Facturas");
        /*mostrar a clientes con facturas: 
        cedula-nombre-celular-deudas
         */

        //producto mas vendido
    }
}