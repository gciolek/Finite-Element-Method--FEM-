# Finite-Element-Method--FEM-
 A project that implements the finite element method
 
 Projekt rozwiązuje nieustalony proces cieplny zachodzący w stworzonej siatce, końcowo wyznaczając maksymalne i minimalne temperatury w siatce.
 Aby rozwiązać problem wykonałam poniższe kroki:
 
A) Wczytałam potrzebne do wykonania zadania dane z pliku tekstowego.  

B) Utworzyłam siatkę MES, która składa się z węzłów i elementów.  

C) Utworzyłam uniwersalny element. 

D) Utworzyłam jakobian służący obliczeniu późniejszych macierzy. 

E) Utworzyłam macierz lokalną H i C.  

F) Wprowadziłam warunki brzegowe na siatkę i utworzyłam macierz warunków brzegowych. 

G) Zagregowałam macierze lokalne H, C i macierz warunków brzegowych.  

H) Utworzyłam wektor P i zagregować go do wektora globalnego.  

I) Rozwiązałam postawiony problem z wykorzystaniem metody Gaussa.  
 
 
