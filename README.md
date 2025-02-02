# Trains_et_Circuits

Ce projet est une **simulation de trains circulant sur une ligne ferroviaire**, en utilisant la programmation concurrente en Java . L'objectif est d'éviter les **collisions**, les **interblocages** (deadlocks) et de garantir une **gestion fluide du trafic**.

### Fonctionnalités du Projet

 **Gestion concurrente des trains** : chaque train est un thread indépendant.  
 **Sécurisation des sections** : un train ne peut entrer dans une section que si elle est libre.  
 **Prévention des interblocages** : les trains en sens inverse attendent que la ligne soit complètement libérée.  
 **Gestion du trafic par direction** : tous les trains d’un même sens doivent terminer avant que la ligne ne change de direction.  


<span style="color: red;">Dans le rendu pdf, nous expliquons en détail l'objectif des classes utilisées, l'évolution du diagramme de classes, ainsi que l'interprétation des méthodes ajoutées.</span>  

**Le projet est structuré de la manière suivante:**

	 /src
	  ├── /train
	  │   ├── BadPositionForTrainException.java
	  │   ├── Direction.java
	  │   ├── Element.java
	  │   ├── Main.java
	  │   ├── Position.java
	  │   ├── Railway.java
	  │   ├── Section.java
	  │   ├── Station.java
	  │   └── Train.java
	  
  
  
### Aperçu des différentes classes
( voir exercice 1 du TP)

### Compilation et exécution du projet
1-  Importez le projet téléchargé au format tar.gz ou .zip sur l'IDE (type Eclipse, IntelliJ, ou VS Code)  	
								
- ou clone du dépôt Git puis ouverture sur l'IDE  

2- Dans le dossier du projet, exécutez : 
  javac *.java  

3- Lancer le programme avec :  	
  java Main


###	Sources d'inspiration
Nous nous sommes inspirés du modèle initial fourni dans l'énoncé de l'exercice et l'avons fait évolué en fonction des exigences du sujet.

###	Difficultés
La première difficulté rencontrée a été de bien comprendre le modèle de simulation ferroviaire ainsi que l'objectif des exercices. Il était essentiel d’identifier les règles de circulation des trains et les contraintes imposées par le système afin de structurer correctement notre solution.  


Une fois cette problématique cernée, un autre défi est apparu : empêcher la circulation des trains en sens inverse tant que des trains circulaient dans un sens donné. Le système initial ne garantissait pas que tous les trains d’une direction terminent leur trajet avant qu’un train en sens opposé ne démarre, ce qui pouvait entraîner des conflits ou des incohérences dans la simulation. Pour résoudre ce problème, nous avons dû introduire de nouvelles variables permettant de compter les trains actifs dans chaque direction et de synchroniser leur passage, assurant ainsi une gestion fluide et réaliste du trafic ferroviaire.

### Auteurs

SORO yiré Asma  

YAO Samuel
  
