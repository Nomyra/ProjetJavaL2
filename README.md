# Table de craft - javaFX

## Models
### Inventaire
HashMap<String,Integer>

Constructeur :
    Inventaire()

Ajouter un item à l'inventaire :
    Ajouter(String nomItem, int quantite)
    S: void
    
Supprimer un item de l'inventaire :
    retirer(String nomItem)
    S: void 
    
Comparer le contenu de l'inventaire avec un autre :
    Inventaire comparer(Inventaire i)
    S: Inventaire (ce qu'il manque dans l'inventaire pour contenir i)
    
    
### Item
String nom //nom de l'objet (nom de l'image privé du .jpg)
String categorie //catégorie de l'objet
String nomRecherche //nom utilisé pour la barre de recherche
boolean fabricable //true si l'objet est fabricable, false sinon
Plan plan //plan permettant de fabriquer l'objet
int nbFabrique //quantité fabriqué par le plan

Constructeur 1 (Item fabricable) :
    Item(Plan plan, String nom, String categorie, int quantite, String nomRecherche)
    
Constructeur 2 (Item non fabricable) :
    Item(String n, String categ, String nomR)

Retourner le hashcode :
    hashCode()
    S : Integer
    
Retourner le nom de l'objet et sa catégorie :
    toString()
    S : String
    
Renvoie les objets nécessaires pour fabriquer l'objet sous la forme d'un objet de classe Inventaire :
    besoins()
    S : Inventaire
    

### Plan
String[][] plan //matrice de 3x3 représentant le plan

Constructeur :
    Plan(String[][] plan)
    
Retourner le hashcode :
    hashCode()
    S : Integer
    
Retourner le plan pour pouvoir l'afficher :
    toString()
    S : String
    
Modifier l'objet d'indice (i,j) du Plan :
    modifierPlan(int i, int j, String nomObjet)
    S : void
    
Supprimer l'objet d'indice (i,j) du Plan :
    modifierPlan(int i, int j)
    S : void
    
Retourner le plan :
    getPlan()
    S : String[][]
    

### TableItems
Hashtable<String,Item>

Constructeur :
    TableItems()
    
Ajouter un Item dans la table :
    ajouter(Item i)
    S : void
    
Renvoyer tous les Item de la table dont l'attribut nomRecherche contient la chaine donnée en entrée sans tenir compte de la casse et des accents :
    rechercher(String nom)
    S : ArrayList<Item>
    
### TablePlans
Hashtable<Integer, Item>

Constructeur :
    TablePlans()
    
Ajouter un Item dans la table :
    ajouter(Item i)
    S : void
    
Renvoyer l'Item correspondant au Plan donné en entrée, ou null s'il n'y a pas :
    chercher(Plan plan)
    S : Item (ou null)


### Modele
ArrayList<String> categories //liste des catégories existantes
ArrayList<String> nom //liste des noms d'item(clés reserve)
TablePlans plans //réserve de plans disponibles
Plan planEnCours //plan en cours sur la table de craft
Inventaire inventaire //inventaire des objets détenus par l'utilisateur (par nom et quantité)
TableItems reserve //liste de tous les items disponibles (fabricables et non fabricables) rangés par nom
boolean modeCreatif //true si le jeu est en mode creatif, false sinon
Item resultatCraft //Item résultant du plan actuellement sur la table (null si le plan ne correspond à rien)

Constructeur :
    Modele()
    
Remplir les ArrayList categories et nom, ainsi que la TablePlans plans et la TableItems reserve avec les données présentes dans le fichier donnees/donnees.txt :
    chargerItem()
    S : void
    
Enregistre dans des fichiers .dat les attributs inventaire, planEnCours et modeCreatif pour sauvegarder l'état de l'application :
    enregistrerEtat()
    S : void
    
Supprime les fichiers de sauvegarde :
    resetSauvegarde()
    S : void
    
Retourner l'attribut resultatCraft :
    getResultatCraft()
    S : Item
    
Mofifier l'attribut resultatCraft :
    setResultatCraft()
    S : void


## Views et Controllers 
JeuxManager -> gère le chargement des pages (init views et controllers) 

HomeController/home.fxml -> Controle le menu de démarage appel JeuxManager pour afficher la partie

MainControler/index.fxml -> Controller principal du jeux, initialise les différents éléments du jeux et gère les intéractions 

MatierePremControler/matierePrem.fxml -> [mode normal] Affichage de la Vbox matière Premiere 

ReserveBox/reserve.fxml -> [mode créatif] Affichage de la Vbox réserve 

HelpPlanControler/help.fxml -> Table de craft initialisé à partir d'un Plan
