# Projet de Communication Distribuée en Java

Ce projet vise à explorer et comparer trois technologies de communication distribuée largement utilisées dans l'écosystème Java : Java RMI, gRPC, et les Sockets Java. À travers la mise en œuvre de trois services distincts, ce projet met en lumière les particularités, les forces et les faiblesses de chaque technologie dans des scénarios d'utilisation variés.

## Technologies Comparées

- **Java RMI** : Permet une invocation de méthode à distance de manière simple et abstraite.
- **gRPC** : Utilise des appels de procédure distante basés sur HTTP/2, offrant une sérialisation efficace avec Protobuf.
- **Sockets Java** : Fournit une communication de bas niveau permettant une flexibilité maximale.

## Structure du Projet

Le code source est organisé en trois répertoires principaux, chacun dédié à une technologie spécifique :

- `JavaRMI` : Implémente un gestionnaire de tâches utilisant Java RMI.
- `gRPC` : Contient le service de messagerie basé sur gRPC.
- `Sockets` : Développe un service de chat simple via les sockets Java.


## Déploiement et Test

### Gestionnaire de Tâches avec Java RMI

Clonez le dépot et Naviguez vers le "Projet".

**Déploiement :**

1. Naviguez vers `JavaRMI`.
2. Exécutez `Server.java` pour démarrer le serveur.
3. Lancez `Client.java` pour ouvrir le client.

**Test :**

- Utilisez le menu du client pour ajouter ou supprimer des tâches, en suivant les instructions fournies via la ligne de commande.

### Service de Messagerie avec gRPC

**Déploiement :**

Prérequis
Avoir Maven installé et configuré sur votre système.
Avoir Java JDK installé et correctement configuré dans votre PATH.

1. Naviguez vers `gRPC_Messagerie`.
2. Compiler le Projet :
   Nettoyer le projet pour supprimer les fichiers de build précédents :
     mvn clean
   Compiler le projet et télécharger toutes les dépendances nécessaires :
     mvn install
   Ceci compile votre code, et génère les fichiers de stubs gRPC nécessaires.

4. Démarrer le Serveur gRPC
5. Démarrer les clients

**Test :**

- Chaque client a le choix d'envoyer un message à un utilisateur donné ou de récuperer la liste des message envoyés à un utilisateur.

### Service de Chat avec Sockets

**Déploiement :**

1. Naviguez vers `Sockets`.
2. Lancez `ChatServer.java` pour lancer le serveur.
3. Exécutez `ChatClient.java` pour démarrer le client.

**Test :**

- Aprèes avoir lancé le serveur, lancez plusieurs clients.
- Chaque client commence par présenter son nom.
- Envoyez des messages par differents client.
- Vérifier que les autres clients déja connecté ont recu les messages des autres.

