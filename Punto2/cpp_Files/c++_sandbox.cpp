#include <bits/stdc++.h>
using namespace std;
//CODIGO CREADO ORIGINALMENTE
void leer(string file_name){
    string line;

    ifstream F(file_name+".txt"); // Busca archivo para abrir
    if(F){ // Si existe
      while (getline (F, line)) {
        cout<<line<<endl; // Escribir lineas
      }
    }else{
        cout<<"Archivo no hallado"<<endl;
    }
    F.close();
}

void search(string file_name, string campo){
    string line;
    bool found=false;
    string d[1];
    
    ifstream F(file_name+".txt"); // Buscar archivo
      while (getline (F, line) and !found) {
          stringstream ssin(line); 
          ssin >> d[0];  // usar streams para obtener campo ID
        if(d[0]==campo){
            cout<<line<<endl; // Escribir si es el campo buscado
            found=true;
        }
        
      }
      if(!found){
          cout<<"ID no hallado"<<endl;
      }
    F.close();
}

void erase(string file_name, string campo){
    string line;
    string d[1];
   
     ifstream F(file_name+".txt"); 
     
    // Crear archivo temporal para copiar
    ofstream T("temp.txt");
    
     while(!F.eof()){
        getline(F,line);
        stringstream ssin(line);
        ssin >> d[0];
        if(!(d[0]==campo)){ // Escribir en archivo temporal si no es el registro por borrar
            T<<line<<endl;
        }
        
     }
    F.close();
    T.close();
    if(remove("Clientes.txt")!=0){ // Borrar archivo original
        cout<<"File not deleted"<<endl;
    }
    if (rename("temp.txt","Clientes.txt") != 0){ // Renombrar el temporal como el original
		cout<<"Error in update"<<endl;
	}
}
int main()
{
    clock_t start, end;
    double delta;
    int op;
    string file_name,campo;
    start = clock(); // Iniciar conteo del tiempo de ejecución
    do{
        cout<<"1.Leer registro según campo ID"<<endl;
        cout<<"2.Borrar registro según campo ID"<<endl;
        cout<<"3.Leer Registros"<<endl;
        cout<<"4. Salir"<<endl;
        cin>>op;
        
        while(op<1 or op>4){
            cout<<"No valido"<<endl;
            cin>>op;
        }
        switch(op){
            case 1:
            
              cout<<"ID a buscar:"<<endl;
              cin>>campo;
              search("Clientes",campo);
              break;
              
            case 2:
            
              cout<<"ID a eliminar"<<endl;
              cin>>campo;
              erase("Clientes",campo);
              break;
              
            case 3: 
              leer("Clientes");
              break;
              
    }

    }while(op!=4);
    end = clock(); // Finalizar conteo de tiempo de ejecución
     delta = double(end - start) / double(CLOCKS_PER_SEC);
     cout<<"runtime: "<<delta<<" sec"<<endl;
    return 0;
}