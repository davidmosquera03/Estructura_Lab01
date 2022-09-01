package Punto2.Java_Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Files_java {
    static int lineasTotales;
    static int coincidencias;
    static Scanner sc = new Scanner(System.in);
    static int choose;
    

    public static void main(String[] args) {

        File file = new File("Clientes.txt");

        int opciones = 0;
        do {

            System.out.println("          Menú de opciones    ");
            System.out.println("Buscar registro según campo ID .........   1");
            System.out.println("Borrar registro según campo ID..........   2");
            System.out.println("Leer Registros..........................   3 ");
            System.out.println("Salir ..................................   4");

            opciones = Integer.parseInt(sc.nextLine());

            switch (opciones) {

                case 1:

                    System.out.println("Ha ingresado a la opción de Buscar en el registro:");
                    System.out.println("--------------------------------------------------");

                    do {
                        System.out.println("Ingrese como quiere hacer su busqueda");
                        System.out.println("Por ID .... 1");
                        System.out.println("Por nombre ..2");
                        System.out.println("Por saldo ....3");
                        choose = Integer.parseInt(sc.nextLine());

                    } while (choose >= 4 || choose <= 0);

                    String palabra = " ";
                    System.out.println("Inserte su busqueda");
                    palabra = sc.nextLine();

                    buscar(file, choose, palabra);

                    break;
                case 2:
                    System.out.println("Ha ingresado a la opción de Borrar en el registro:");
                    System.out.println("--------------------------------------------------");

                    do {
                        System.out.println("Ingrese como quiere hacer su busqueda");
                        System.out.println("Por ID .... 1");
                        System.out.println("Por nombre ..2");
                        System.out.println("Por saldo ....3");
                        choose = Integer.parseInt(sc.nextLine());

                    } while (choose >= 4 || choose <= 0);

                    System.out.println("Ingresa lo que quieres borrar");
                    String LineaRemover = sc.nextLine();

                    
                    eliminar(file, LineaRemover, choose);
                    
                    break;
                case 3:
                    System.out.println("Ha ingresado a la opción de leer el registro:");
                    System.out.println("--------------------------------------------------");

                    leer(file);

                    break;
                default:

                    break;
            }

        } while (opciones != 4);

        
    }

    static void leer(File file) {
        // String texto = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String temp = " ";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead;
                System.out.println(bfRead);
            }

            
            
            bf.close();

        } catch (IOException e) {
            System.out.println(" No se encontro el archivo " + e);
        }
    }

    static void buscar(File file, int choose, String palabra) {

        try {

            // si el archivo existe
            if (file.exists()) {
                // Abrir lectura del archivo
                BufferedReader LeerArchivo = new BufferedReader(new FileReader(file));
                // Linea leida
                String lineaLeida;

                // Mientras la linea leida no sea null
                while ((lineaLeida = LeerArchivo.readLine()) != null) {

                    // aumentar contador de lineas
                    lineasTotales++;
                    String palabras[] = lineaLeida.split("\\s");
                    if (palabras[choose - 1].equals(palabra)) {
                        coincidencias++;
                        System.out.println("Esta es la linea completa");
                        System.out.println(lineaLeida);
                        System.out.println("En la linea: " + lineasTotales + " se encontro el argumento: " + palabra);
                        System.out.println(" ");
                    }

                    // Recorro el arreglo de palabras
                    // for (int i = 0; i < palabras.length; i++) {
                    // si la palabra es igual a la que estoy buscando
                    // debe entrar al condicional
                    // if(palabras[i].equals(palabra)) {

                    // coincidencias++;
                    // System.out.println("Esta es la linea completa");
                    // System.out.println(lineaLeida);
                    // System.out.println("En la linea: " + lineasTotales + " se encontro el
                    // argumento: " + palabra );
                    // System.out.println(" ");
                    // }
                    // }

                }
                LeerArchivo.close();

            }
            if (coincidencias > 0) {
                System.out.println("En total se encontro -> " + palabra + " , " + coincidencias + " veces ");
            } else {
                System.out.println("Error no se encontro la busqueda");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + e);
        }

    }

    public static void eliminar(File file, String LineaRemover, int choose) {

        File tempFile = new File("myTempFile.txt");

        try (BufferedReader leer = new BufferedReader(new FileReader(file))) {
            BufferedWriter escribir = new BufferedWriter(new FileWriter(tempFile));

            String LineaActual;

            while ((LineaActual = leer.readLine()) != null) {

                String lineasRemover[] = LineaActual.split("\\s");
                if (!lineasRemover[choose - 1].equals(LineaRemover)) {

                    escribir.write(LineaActual + System.getProperty("line.separator"));

                }

                

            }

            escribir.close();
            leer.close();

            boolean borrarf = file.delete();
            boolean actualizar = tempFile.renameTo(new File("Clientes.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
