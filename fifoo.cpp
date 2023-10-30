#include<bits/stdc++.h>
using namespace std;
int main(){
int n;
cin >> n;
vector<int> v(n);
int k;
cin >> k;
vector<int>p(k);
for(int i=0;i<k;i++){
    cin>>p[i];
}

for(int i=0;i<n;i++){
v[i] =p[i];
}
for(int i=0;i<n;i++){
    cout<<v[i]<<" ";
}
for(int i=0;i<k;i++){
    cout<<p[i]<<" ";
}

int pf(0),pgh(0);
int cnt(0);
for(int i=n;i<k;i++){
cnt=cnt%n;
cout<<"cnt"<<cnt;
bool ok =false;
    for (int  j = 0; j< n; j++)
    {
        cout<<v[j]<<"vv  ";
        if(p[i]==v[j]){
            ok=true;
        }


    }
    if(ok){
      
pgh++;
     }
     else{
        cout<<p[i]<<" p";
        pf++;
       v[cnt]=p[i]; 
cnt++;

     }
    }
    

cout<<"pagfa"<<pf<<endl;
cout<<"pghit"<<pgh<<endl;
for(int i=0;i<n;i++){
    cout<<v[i]<<"  ";
}

    return 0;
}