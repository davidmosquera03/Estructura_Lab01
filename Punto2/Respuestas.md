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

## b) Métricas 

### Runtime promedio (en segundos)

|            | Java      | Python |  C++    |
|------------|-----------|------- |---------|
|Buscar registro 1|      X    |      X |     0.0002885s    |
|Buscar registro 10000|   X   |      X |   0.005393s  |
|Borrar registro 1|     X     |      X |    0.024419s     |
|Borrar registro 10000|     X     |      X |   0.024002s     |
|Leer registros |     X     |      X |    0.0589s     |


### Lineas de codigo 

| Java      | Python |  C++    |
|-----------|------- |---------|
|      X |    X     |111|

### Complejidad 

| Java      | Python |  C++    |
|-----------|------- |---------|
| O( N )    |   O( N )  |O( N )|

**c) En qué se diferencian (si es que se diferencian) los algoritmos que trabajan sobre archivos en la actualidad de los vistos en clase? Explicar su respuesta.**




