
from pickle import TRUE
import os




def buscar(buscar1):
    
    with open("Clientes.txt", 'r') as myfile:
       
        for line in myfile:
            Nlinea=line.split('\t')
            if buscar1 in Nlinea:
                print(f'{Nlinea}')
        
        

def borrar(borrado):
       
    lineas = list()
    with open("Clientes.txt", "r") as input:
        lineas = input.readlines()
        
        
    with open("temp.txt", "w") as output:
            
            # iteracion osbre todas las lineas de la fila
        for index, line in enumerate(lineas):
            if index not in [int(borrado)-1]:
                output.write(line)
               
                    
        print("La linea a sido borrada exitosamente")

    # replace file with original name
    os.replace('temp.txt', 'Clientes.txt')


def leer():
    files = open("Clientes.txt", 'r') 
    for line in files:
        print(line)
    files.close()
    

def opcionNumero():
    correcto=False
    numero=0
    while(not correcto):
        try:
            numero = int(input("Introduce un numero entero: "))
            correcto=True
        except ValueError:
            print('Error, introduce un numero entero')
     
    return numero



salir = False
opcion = 0

while not salir:
    print("     Menú de opciones      ")
    print ("Buscar registro según campo ID .........   1")
    print ("Borrar registro según campo ID..........   2")
    print ("Leer Registros..........................   3 ")
    print ("Salir ..................................   4")
    print ("Elige una opcion")
    
    opcion = opcionNumero()
 
    if opcion == 1:
        print ("Ha ingresado a la opción de Buscar en el registro:")
        print("--------------------------------------------------")
        print("")
        print("¿Qué quieres buscar?")
        buscar1 = input()
       
        buscar(buscar1)
        
        
    elif opcion == 2:
        print ("Ha ingresado a la opción de Borrar en el registro:")
        print("--------------------------------------------------")
        print("")
        
        print("Inserte lo que quiere borrar")
        borrado = input() 
        
        borrar(borrado)
        
        
    elif opcion == 3:
        print("Ha ingresado a la opción de leer el registro:")
        print("--------------------------------------------------")
        print("")
        
        leer()
        
    elif opcion == 4:
        
        salir = True
       
    else:
        print ("Introduce un numero entre 1 y 3")

print ("Fin")
