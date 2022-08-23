#include <bits/stdc++.h>
using namespace std;

void llenar(string file_name){
    string line,ID,name,balance;
     bool hay = false;
     
    ofstream F(file_name+".txt");
    cout<<"Is there info? 1-yes 0-false"<<endl;
    cin>>hay;
    while(hay){
        cout<<"ID"<<endl;
        cin>>ID;
        cout<<"Name"<<endl;
        cin>>name;
        cout<<"Balance (positive value)"<<endl;
        do{
            cin>>balance;
        }while(stoi(balance)<0);
        if(!(ID.empty()) && !(name.empty()) && !(balance.empty())){
            line=ID+"\t"+name+"\t"+balance;
        }
        F<<line<<endl;
        cout<<"Is there info? 1-yes 0-false"<<endl;
        cin>>hay;
    }
    
    F.close();
}

void leer(string file_name){
    string line;

    ifstream F(file_name+".txt"); // Searches file to open it
    if(F){ // Check it exists
      while (getline (F, line)) {
        cout<<line<<endl;
      }
    }else{
        cout<<"File not found"<<endl;
    }
    F.close();
}

void search(string file_name, string campo){
    string line;
    bool found=false;
    string d[1];
    
    ifstream F(file_name+".txt"); // Searches file to open it
      while (getline (F, line) and !found) {
          stringstream ssin(line);
          ssin >> d[0];
        if(d[0]==campo){
            cout<<line<<endl;
            found=true;
        }
        
      }
      if(!found){
          cout<<"ID not found"<<endl;
      }
    F.close();
}

void erase(string file_name, string campo){
    string line;
    string d[1];
    // Open file to read it
     ifstream F(file_name+".txt"); // Searches file to open it
     
    //Create tempFile to write onto it
    ofstream T("temp.txt");
    
     while(!F.eof()){
        getline(F,line);
        stringstream ssin(line);
        ssin >> d[0];
        if(!(d[0]==campo)){ // Copy if not on register to delete
            T<<line<<endl;
        }
        
     }
    F.close();
    T.close();
    string oF= file_name+".txt";
    if(remove("Arc.txt")!=0){ // Erase old file
        cout<<"File not deleted"<<endl;
    }
    if (rename("temp.txt","Arc.txt") != 0){ // Rename temp as original
		cout<<"Error in update"<<endl;
	}
}
int main()
{
    clock_t start, end;
    double delta;
    int op;
    string file_name,campo;
    start = clock(); //start count for runtime
    do{
        cout<<"1.Find register based on ID"<<endl;
        cout<<"2.Delete register based on ID"<<endl;
        cout<<"3.Exit"<<endl;
        cin>>op;
        
        while(op<1 or op>3){
            cout<<"No valido"<<endl;
            cin>>op;
        }
        switch(op){
            case 1:
            
              cout<<"ID a buscar:"<<endl;
              cin>>campo;
              search("Arc",campo);
              break;
              
            case 2:
            
              cout<<"ID a eliminar"<<endl;
              cin>>campo;
              erase("Arc",campo);
              break;
              
    }

    }while(op!=3);
    end = clock(); // End count for runtime
    delta = double(end - start) / double(CLOCKS_PER_SEC);
    cout<<"runtime: "<<delta<<" sec"<<endl;
    return 0;
}