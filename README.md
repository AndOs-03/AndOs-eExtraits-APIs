# AndOs-eExtraits-APIs
AndOs eExtraits est un logiciel de génération d'extraits d'États Civils (`Naissances, Mariages, 
Décès`)

# INSTALLER LES FONTS EMBARQUÉES DE JASPER
- Calibri : `mvn install:install-file -"Dfile=src/main/resources/fonts/jasperfont-calibri.jar" 
  -DgroupId="com.andos.jasperfonts" -DartifactId="jasperfont-calibri" -Dversion="1.0" -Dpackaging=jar`


- Georgia : `mvn install:install-file -"Dfile=src/main/resources/fonts/jasperfont-georgia.jar" 
  -DgroupId="com.andos.jasperfonts" -DartifactId="jasperfont-georgia" -Dversion="1.0" 
  -Dpackaging=jar`

# DEPLOYER SUR UN SERVEUR
- BASE DE DONNEES:

*Commande en local*

    `scp -r andos-eextraits-bdd root@ip-mon-server:/tmp/`

*Commande pour le server en ligne*

    `scp -r andos-eextraits-bdd root@ip-mon-server:/tmp/`
