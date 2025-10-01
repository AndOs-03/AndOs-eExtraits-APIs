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
- Copie de dossier : `scp -r /chemin/dossier/fonts user@IP_SERVER:/chemin/dossier/fonts`
- Copie de fichier : `scp /chemin/dossier/fonts/fonts.txt user@IP_SERVER:/chemin/dossier/fonts`
