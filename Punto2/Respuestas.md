# Respuestas

 
**a)Todos los lenguajes de programación usan los mismos métodos para trabajar con archivos (tanto para lectura como escritura)? Explicar su respuesta/describir un ejemplo.**

No. Si bien los métodos pueden hacer aparentemente lo mismo (abrir en diferentes modos un archivo o cerrarlo), hay diferencias notables en como se implementan y los algortimos que pueden usarse, tanto entre lenguajes compilados como C++ y Java como en referencia a interpretados como Python. Por ejemplo:

### Abrir archivo

Python
```
f = open(<path>,"r") 
```
No es necesario importar una libreria para abrir archivos. De no hallar archivo con el nombre dado en la ruta, se crea uno, en vez de arrojar error.

C++
 ```
#include <iostream>
#include <fstream>
using namespace std;

int main{
    ifstream f(<path>); 
    if(f){
        cout<<"File Found<<endl;
    }else{
        cout<<"File not Found<<endl;
    }
    return 0;
}
```
Se deben incluir clases como *iofstream* y *fstream*, y se usa un objeto de*ifstream* (en este caso llamado *f*) para  abrir el archivo. Se puede usar un condicional para verificar el exito de la operación.

Java
```
import java.io.BufferedReader;

 // Dentro de la clase principal...
try{
    BufferedReader br = new BufferedReader(new  FileReader(<path>));
    (...)
}catch(IOException ex){
    ex.printStackTrace();
}

```
Se pueden importar diferentes librerias además de BufferedReader pero es necesario instanciar un objeto de la clase y usar un try catch para excepciones por si no se encuentra el archivo.
### Recorrer un archivo
Python
```
for line in f:
    print(line)
```
Sin necesidad de referirnos al End of File o recorrer el archivo con un condicional (lo que tambien se puede hacer), el *for* de python permite iterar por el archivo y escribir registros con la misma variable.

C++
 ```
 string line;
 while(getline(f,line)){
    cout<<line<<endl;
 }

```
Usando la funcion *getline*, que obtiene un registro dentro de un archivo *f* y lo guarda en un string como line, podemos recorrer el archivo mientras reciba algo la función.

Java
```
String line;
while ((line = br.readLine()) != null) {
    System.out.println(line)
    }
```
En java podemos usar una validación bastante similar usando la funcion readline de BufferedReader.

### Escribir a un archivo
Python
```
f = open(<path>,"w")

f.write("Hello World")

```

C++
 ```
 ofstream f(<path>);
 f<<"Hello World"<<endl;
```
Se abre simplemente en modo escritura con ofstream y se usa el operador de inserción "<<" hacia 

Java
```
//dentro de la clase principal y try-catch

PrintWriter reg_f = new PrintWriter(new FileWriter(Temp));
reg_f.println("Hello World");
```
Nuevamente hay diferentes librerias disponibles, sin embargo es necesario instanciar objetos para poder realizar las operaciones.

## b)
## Runtime promedio (en segundos)

|            | Java      | Python |  C++    |
|------------|-----------|------- |---------|
|Buscar registro 1|    3s  |  2.97143s |     0.0002885s    |
|Buscar registro 10000|   4s |  3.76329469s |   0.005393s  |
|Borrar registro 1|      4s   |  3.254875s |    0.024419s     |
|Borrar registro 10000|  5s |  3.5780220s |   0.024002s     |
|Leer registros |      7s    |     5.914s |    0.0589s     |

### Conclusiones:
- C++ fue el más rápido, luego Python con una gran diferencia.
- Java es el más lento, pues es despues de muchas mas iteraciones que su velocidad se demuestra mas eficaz.
[**](https://www.quora.com/Why-is-Java-slower-than-Python-and-C++-Leetcode-solutions-runtime-distribution-Is-that-always-the-case)
- Borrar el registro 1 o 10000 toma tiempo similar, puesto que involucra de todas formas copiar el archivo original
- Leer el registro 1 claramente toma menos tiempo que leer el ultimo, al requerir menos iteraciones


## Lineas de codigo 

| Java      | Python |  C++    |
|-----------|------- |---------|
|    187    |   100  |   111   |

## Complejidad 

| Java| Python |  C++    |
|--|--|--|
|O(N)|O(N)|O(N)

Puesto que la estrucura mas compleja fueron ciclos, que realizan N operaciones, la complejidad es esta.
No se usó ninguna recursividad, ni ningun ciclo aninado que huberian dejado complejidades logaritmicas o exponenciales.

**c) En qué se diferencian (si es que se diferencian) los algoritmos que trabajan sobre archivos en la actualidad de los vistos en clase? Explicar su respuesta.**
Los algortimos trabajados en clase de abrir, cerrar, leer y escribir archivos son iguales a los trabajados en la actualidad. Sin embargo el método de actualizar archivos en la práctica si ha cambiado.
Antes se usaban 3 archivos (como se muestra en las diapositivas de clase), el original, el creado con los cambios y en el que se guarda el cruce de los primeros dos archivos y remplaza al original.
Como se explicó en clase, hoy no se suele crear otro archivo con los cambios. Se utiliza un algortimo con una "lógica" que permita pasar la información actualizada al nuevo archivo que remplaza al original.



