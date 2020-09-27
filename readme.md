## Comment exécuter ? 
### lancer le serveur base de données
- télélcharger le zip de la base H2: https://h2database.com/h2-2019-03-13.zip
- dézipper le
- dans la racine du dossier h2, créer la base mczendb lancant la commande: `java -cp bin/h2-1.4.199.jar org.h2.tools.Shell`
    - url:  `jdbc:h2:~/tmp/h2dbs/mczendb`
    - user: `sa`
    - password: `sa`
- dans la racine du dossier h2, démarrer le serveur de la base en lancant la commande: `java -jar bin/h2-1.4.199.jar -baseDir ~/tmp/h2dbs`
- depuis le navigateur, aller sur l'addresse http://localhost:8082/test.do
    - se connecter à la base mczendb précedemment créer en utilisant les paramètres définis ci-dessus
- initialiser la tabele LOGINLOGS en lançant le script sql `src/main/resources/sql/init_db.sql` 

### lancer l'application mczen
#### methode 1 (depuis IDE)
- lancer `mvn compile` pour télécharger les dépendances et compiler
- exécuter la classe Application
#### methode 2
- builder le projet avec `mvn clean package`
- lancer la commande java -jar /target/com.mczen.api-1.0.0-SNAPSHOT-with-dependencies.jar`
- visiter l'url : `http://localhost:8080/api/login-events`

## Détails technique : (voir  l'image architecture_physique.png)
### MVC: https://medium.com/@belcaid.mehdi/larchitecture-logicielle-mvc-1a8bbb5cf6dc
### java + maven
### JAX-RS
- jersey: https://fr.wikipedia.org/wiki/Jersey_(framework)
- grizzly2: pour lancer rapidement un serveur web (gagner du temps pour éviter de configurer un serveur tomcat)
- j'ai utilisé la confihuration par java et non pas par web.xml
### H2
- éviter d'insatller et configurer un SGBD (mysql, sql server, postgresql...)
- pas besoin d'installation d'un serveur H2. Le travail se fait avec les jar
### test
- junit 
- mockito: mocker facilement les classes

### perspective
- Option 1: ajouter un web.xml, builder un war et le déployer sous tomcat.
- Option 2: migrer vers spring boot


