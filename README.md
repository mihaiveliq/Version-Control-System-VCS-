# Version-Control-System-VCS-

  VCS (Version Control System) permit gestionarea versiunilor multiple ale fișierelor. 
Fiecare modificare a unui fișier este înregistrată în sistem. O astfel de înregistrare poartă numele de commit. 
In cadrul temei cand vom spune că am dat un commit, înseamnă că am salvat versiunea curenta a tuturor fișierelor
din sistemul de fișiere. La finalul implementării aplicația trebuie sa ne permită sa dăm commituri și să putem 
să revenim la o versiune anterioară a sistemului de fișiere, adică să ne întoarcem câteva commit-uri în trecut.

Cursorul HEAD:

La capătul acestui flow de commit-uri se află un cursor denumit HEAD. În momentul adăugării unui nou commit 
sau întoarcerii la un commit anterior, cursorul HEAD se mută pe commit-ul respectiv.

Semnificația unui branch:

Sistemele de versionare permit si lucrul mai multor persoane pe un set de fișiere. Astfel, a apărut noțiunea de branch. 
Branch-ul este o ramură a sistemului de fișiere. Practic, putem sa avem același sistem de fișiere duplicat pe mai 
multe branch-uri ceea ce ne permite ca fiecare dezvoltator să lucreze pe branch-ul său.

Branch-ul master:

Branch-ul principal se numește master. În momentul în care ne mutăm de pe un branch pe altul efectuăm o operație 
de checkout. Astfel, pointerul HEAD este mutat pe branch-ul pe care dorim să ajungem.

Staging:

Cand întâlnim notiunea de “Staged changes”, ne referim la operațiile care încă nu au fost “commit-uite” si care au
alterat starea sistemului de fișiere. De exemplu, daca de la ultima comandă de commit am mai creat un fișier, această 
operație de touch va fi adăugată în staging. În momentul în care se execută operația vcs commit, atunci staging-ul va 
fi golit, iar HEAD-ul se va muta pe noul commit. În momentul în care se execută operația vcs rollback, atunci 
staging-ul va fi golit, HEAD-ul va rămâne pe commit-ul curent, iar activeFileSystemSnapshot va reveni la valoarea din 
commit-ului curent. 
