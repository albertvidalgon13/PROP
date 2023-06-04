# Projecte de Prop Q1 2022/23

Projecte de l'assignatura de PROP de la Facultat d'Informàtica de Barcelona

----

#### Integrants del grup

Albert Vidal González

Ignasi Juez Guirao

Lluís Lllull Riera

Iván Serrano Hernández

`Professor:` Miquel Sànchez

miquel@cs.upc.edu

---

## Descripció del sistema

Aquest projecte tracta de construir un entorn per a la manipulació de documents. El programa ofereix un entorn fàcilment manipulable amb les següents funcionalitats:

- carregar documents (individualment o en conjunt), a partir de diferents formats
  (text pla i xml) i guardar documents a diferents formats (text pla
  i xml). 

- altes, baixes i modificacions de documents

- Els següents tipus de consultes:
  
  - Llista de títols d’un autor
  
  - Llista d’autors que comencen per un prefix (que pot ser buit)
  
  - Contingut d’un document donat el seu títol i autor
  
  - Llista de documents per dos mètodes:
    
    - Donat un document D i un natural k, obtenir els k documents (tan sols títol i autor) més semblants a D
    
    - Donades una expressió booleana formada pels operadors & | i ! (amb les normes de precedència habituals i la possibilitat de parentitzar per canviar aquesta precedència) i conjunts de paraules (delimitats per {}), seqüències de paraules (delimitades per “”), o paraules soltes com a operands, obtenir tots els documents que contenen una frase que satisfà aquesta expressió. Un exemple d’expressió seria:
      
      {p1 p2 p3} & (“hola adéu” | pep) & !joan
      
      Una frase satisfà aquesta expressió si conté les 3 paraules p1, p2 i p3
      i conté la seqüència “hola adéu” o la paraula pep
      i no conté la paraula joan
    
    - donades *p* paraules (denotades col·lectivament com a query), i un enter k, obtenir els k documents més rellevants (en quant a contingut) per aquesta query.

Els resultats de les diferents consultes es poden ordenar per diferents criteris.

## Funcionament Intern

Per tal de gestionar les cerques de forma eficient, ja que són el pilar d'aquest projecte, es fa servir un model d'espai vectorial (*file term vector*) per tal d'assignar pesos a les paraules de cada document, i un fitxer invertit per poder obtenir dades sobre cada *token* del sistema de documents.

## Usage

---

Existeixen dos formes d'executar l'aplicació:

- Amb l'executable que es troba a la carpeta **/EXE**

- Des del directori **/FONT**,  fent doble clic en l'arxiu .bat, que compila tot el codi i després l'executa

En qualsevol dels dos casos, **no eliminar cap dels documents de la carpeta `metada`**, ja que conté arxius de la persistència necessaris pel correcte funcionament de l'aplicació.
