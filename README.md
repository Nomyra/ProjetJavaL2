# Table de craft - javaFX

## Models
###Reserve 
HashMap<String,Integer>

Ajouter un item à l'inventaire :
    Ajouter((String nomItem, int quantite))
    S: void
    
Supprimer un item de l'inventaire :
    retirer(String nomItem)
    S: void 
:
    Inventaire comparer(Inventaire i)
    S: Inventaire 


## Views et Controllers 
JeuxManager -> gère le chargement des pages (init views et controllers) 

HomeController/home.fxml -> Controle le menu de démarage appel JeuxManager pour afficher la partie

MainControler/index.fxml -> Controller principal du jeux, initialise les différents éléments du jeux et gère les intéractions 

MatierePremControler/matierePrem.fxml -> [mode normal] Affichage de la Vbox matière Premiere 

ReserveBox/reserve.fxml -> [mode créatif] Affichage de la Vbox réserve 

HelpPlanControler/help.fxml -> Table de craft initialisé à partir d'un Plan