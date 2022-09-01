package Punto1_Java;

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
public static void deudas() {
        try {
            BufferedReader reg_c = new BufferedReader(new FileReader("Clientes.txt")); // Abre archivo Clientes
            BufferedReader reg_f = new BufferedReader(new FileReader("Facturas.txt")); // Abre archivo Facturas
            BufferedReader reg_p = new BufferedReader(new FileReader("Productos.txt")); // Abre archivo Facturas

            String line_c = reg_c.readLine();
            String line_f = reg_f.readLine();
            //Leer primeros registros

            while (line_c != null && line_f != null) { // Detenerse cuando uno de los dos archivos se acabe

                String cdata[] = line_c.split("\t");
                String fdata[] = line_f.split("\t");
                // Obtener datos con split
                String cedc = cdata[0];
                String cedf = fdata[0];

                // Obtener valores de cedulas para comparar
                if (!cedc.equals(cedf)) { // Cedulas diferentes, avanzar en Clientes
                    line_c = reg_c.readLine();

                } else {

                    int totc = 0; //Acumulador de deuda por Cliente con factura
                    while (cedc.equals(cedf)) {

                        // Mismas cedulas, recorrer Facturas y Productos
                        boolean found = false;
                        String prodf = fdata[2]; // Numero de producto en Factura
                        int cantidad = Integer.parseInt(fdata[3]); 
                        int valor = 0; // Valor del producto (Por buscar en Productos)

                        while (!found) { // Mientras no se ha hallado el producto en Productos
                            String line_p = reg_p.readLine();
                            if (line_p == null) { // Si se llega al fin de Productos, volver al inicio reabriendolo
                                reg_p.close();
                                reg_p = new BufferedReader(new FileReader("Productos.txt"));
                                line_p = reg_p.readLine();
                            }
                            String pdata[] = line_p.split("\t");
                            String prodp = pdata[0];
                            // Obtener numero de producto en registro actual de Productos
                            if (prodp.equals(prodf)) {
                                valor = Integer.parseInt(pdata[2]); // Guardar valor de producto
                                found = true;
                            }

                        }

                        //System.out.println("cantidad es  "+cantidad+" valor es "+valor);
                        totc = totc + (cantidad * valor);
                        //System.out.println("totc es "+totc);

                        line_f = reg_f.readLine();
                        if (line_f == null) { //Salir si acaban las facturas
                            break;
                        }
                        fdata = line_f.split("\t");
                        cedf = fdata[0];

                    }
                    System.out.println("Cedula: " + cedc + " Nombre: " + cdata[1] + " Celular: " + cdata[3] + " Deuda: " + totc);
                }

            }
            reg_c.close();
            reg_f.close();
            reg_p.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

     public static void mas_vendido() {

        try {
            BufferedReader reg_f = new BufferedReader(new FileReader("Facturas.txt"));
            BufferedReader reg_p = new BufferedReader(new FileReader("Productos.txt"));

            int n_max = -1;
            String p_max = "";
            int cant_p = 0;

            String line_f = reg_f.readLine();
            String line_p = reg_p.readLine();
            while (line_p != null) { // Hasta revisar todos los productos
                String pdata[] = line_p.split("\t");
                String fdata[] = line_f.split("\t");
                String prodp = pdata[0];
                String prodf = fdata[2];

                if (prodp.equals(prodf)) { // Aumentar cantidad de producto actual si coincide en factura
                    cant_p += Integer.parseInt(fdata[3]);

                }
                line_f = reg_f.readLine();
                if (line_f == null) { // Si se acaban las facturas
                    if (cant_p > n_max) { // Ver si cantidad de producto actual es mayor
                        n_max = cant_p; //Guardar cantidades
                        p_max = pdata[1]; // Guardar Nombre
                    }
                    reg_f.close();
                    reg_f = new BufferedReader(new FileReader("Facturas.txt")); // Reabrir facturas
                    cant_p = 0; // Reiniciar conteo 
                    line_p = reg_p.readLine(); // Cambiar de producto
                    line_f = reg_f.readLine();
                }

            }
            System.out.println("El producto más vendido es " + p_max + " Con " + n_max + " unidades");
            reg_p.close();
            reg_f.close();
                   
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Mostrar desordenados
        System.out.println("Clientes");
        LeerC(sc, "Clientes");
        System.out.println("");

        System.out.println("Facturas");
        LeerFac(sc, "Facturas");
        System.out.println("");

        //Ordenar Archivos
        sortF("Clientes");
        sortF("Facturas");
        
        System.out.println("Clientes");
        LeerC(sc, "Clientes");
        System.out.println("");
        
        System.out.println("Facturas");
        LeerFac(sc, "Facturas");
        System.out.println("");

        /*mostrar a clientes con facturas: 
        cedula-nombre-celular-deudas
         */
        System.out.println("Clientes con Facturas");
        deudas();

        //producto mas vendido por cantidad
        mas_vendido();

        System.out.println("FIN DEL PROGRAMA");
    }
}