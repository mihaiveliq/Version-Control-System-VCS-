# Version-Control-System-VCS-

Prezentarea implementarii:
    -Fisierele primite ca parametru sunt cel de input, in care se afla
    un set de comenzi in forma unor stringuri, si cel de output, in care 
    sunt scrise rezultatele generate de unele comenzi. 
    -Pentru gestionarea comenzilor este necesara creearea unei instante a 
    clasei "Context", care are 4 campuri: 
        1) o instanta a propriei clase, care initial este null, instantierea
        avand loc atunci cand se apeleaza metoda getInstance(); in felul 
        acesta se permite o instantiere unica, mai exact un context anumit.
        2) un obiect de tip "Vcs", care este o clasa de tip Visitor; 
        cu ajutorul acestui obiect se va creea si prelucra sistemul de 
        fisiere (in cazul nostru, doar niste obiecte).
        3) si 4) instante de inputWriter, respectiv outputWriter, necesare
        pentru citirea si afisarea din si in fisiere.
    -Dupa instantierea contextului, acesta este initializat, continuand cu
    apelarea pe metodei sale run(). Practic in momentul acesta se prelucreaza
    toate comenzile din input, creindu-se sistemul de fisiere si adus la forma
    finala, dupa cum voi descrie in cele ce urmeaza (voi detalia ce se 
    intampla in run(), dar dupa amintirea unor mici precizari din cerinta).
    -Stringurile continand comenzile sunt retinute pe rand intr-un string,
    folosit ca parametru de metoda "parseOperation" a obiectului de tip
    "OperationParser" (declarat la inceput in run()) returnand operatia
    continuta de stringul curent. Citirea din fisierul de input se incheie
    la intalnirea stringului "exit". Revenind la operatia returnata care
    este retinuta intr-o instanta de tip "AbstractOperation", un prototip
    al oricarei operatii posibil aplicabile asupra sistemului de fisiere 
    cerut de cerinta. Clasa abstracta AbstractOperation contine doua campuri:
        1) tipul operatiei, care este o instanta a enumeratiei 
        "OperationType", este dat ca prim parametru in constructorul unei
        operatii. 
            ->(Digresiune)OperationType contine operatiile cerute de 
        cerinta, aflandu-se in plus doua operatii care vor reprezenta
        orice operatie care nu se afla printre cele specificate in 
        cerinta temei, respectiv InvalidFileSystemOperation, pentru
        orice operatie de FyleSystem gresit formulata, si InvalidVcsOperation,
        analog pentru cele de tip Vcs. Ca o ultima specificatie inainte de a
        insera o noua digresiune referitoare la componenta sisteului de 
        fisiere si a sistemului de versionare, operatiile de FyleSystem 
        actioneaza asupra sistemului de fisiere, iar cele de tip Vcs, evident,
        il gestioneaza pe cel de versionare, adica de creearea si manipularea
        diferitelor versiuni ale unui anumit sistem de fisiere. Sistemul de
        fisiere este compus din niste entitati abstracte, extinderi ale clasei
        abstracte "FileSystemEntity", mai precis "File" si "Directory". Din
        aceste tipuri, pe baza comenzilor de FyleSystem se va alcatui sistemul
        de fisiere. Sistemul de versionare va fi alcatuit din stari ale 
        sistemului de fisiere la un moment dat, reprezentate de obiecte de
        tip Commit, starea aceluiasi, sau a unui diferit Commit putand fi
        actualizata, sau regresata in paralel de pe obiecte de tip Branch.
        Branch ul curent, este cel care contine si cel mai recent commit.          
        2) argumentele sunt retinute intr o lista de stringuri generata 
        de obiectul de tip OperationParser, si este dat ca al doilea
        parametru in constructorul unei operatii.
    -Pe baza operation type ului si a argumentelor, cu ajutorul 
    clasei OperationFactory este creata fiecare operatie, reprezentand 
    o instanta a unei clase ce extinde AbstractOperation, continand 
    functii care executa operatia respectiva.
    -Fiecare operatie accepta executia obiectului Vcs, al carui camp 
    activeSnapshot contine versiunea actuala a sistemului de fisiere,
    pe baza careia actioneaza operatiile.
    -In urma executiei, in fisierul de output se afla detalii generate
    pe baza codurilor returnate de metoda execute a fiecarei operatii.
    -Codurile de erori sunt generate de clasa ErrorCodeManager.
