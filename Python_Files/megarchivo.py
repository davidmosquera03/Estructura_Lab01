# Codigo usado para generar archivo de 10000 registros aleatorios
import names
from random import seed
from random import randint
seed(1)
f = open("Clientes.txt", "w")
for i in range(10000):
    n= names.get_first_name()
    saldo=randint(0,1000000)
    ced=i+1
    line=str(ced)+"\t"+n+"\t"+str(saldo)
    f.write(line+"\n")
f.close()