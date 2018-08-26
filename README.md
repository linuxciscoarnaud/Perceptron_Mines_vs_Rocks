# Perceptron_Mines_vs_Rocks
Implémentation java du modèle de classification des signaux sonar (Mines vs Roches) en faisant usage de l'algorithme du perceptron.

Ce code est une implémentation java du modèle de classification des signaux sonar (Mines vs Roches), en se servant de l’algorithme de classification linéaire qu’est le perceptron. La tâche de l’algorithme consiste ici à apprendre à distinguer les signaux sonar renvoyés par un cylindre de métal et ceux qui rebondissent sur une roche grossièrement cylindrique. Les navires de guerre et les sous-marins s’en servent par exemple pour détecter les mines sous-marines.

L’implémentation est réalisée à partir de zéro et ne fait intervenir aucune librairie java d’apprentissage automatique telle que DeepLearning4j ou weka. Le programme peut aisément s’exécuter sur le CPU.

Les données d'entrainement et ceux de test ont été développé en collaboration avec R. Paul Gorman du Centre de technologie aérospatiale Allied-Signal. Elles peuvent être téléchargées via le lien suivant : https://archive.ics.uci.edu/ml/datasets/Connectionist+Bench+(Sonar,+Mines+vs.+Rocks) Elles sont respectivement chargées en mémoire à partir des fichiers sonar.all-data.csv et sonarTesDdata.csv qui se trouvent dans le répertoire du code source. Les 208 données d’entrainement ainsi que leurs étiquettes, telles que fournies sont facilement lues et chargées en mémoire par la fonction void LoadTrainingData(double[][] train_X, int[] train_T) de la classe Dataset.java. En revanche, les données de test et leurs étiquettes telles que fournies n’ont pas pu directement être lues (J’y travaille encore). J’ai dû pour l’instant, manuellement les mettre sous une forme semblable à celle des données d’entrainement afin de pouvoir les charger en mémoire par la fonction void LoadTestData(double[][] test_X, int[] test_T). Pour l’instant, 20 données de test sont disponibles.

Le fichier resultsFile.xls a été généré dans le repertoire de l'application afin de permettre une visualisation et une comparaison des résultats obtenus. Toutes les informations sont contenues dans le feuille "Mines_vs_Rocks". Le fichier comporte 3 colonnes qui representent respectivement les étiquettes des données de test, les étiquettes des données de test qui ont été prédites et enfin les valeurs réelles de prédiction avant application de la fonction d'activation.

L'évaluation de la performance du programme, en tenant compte des données de test disponibles donne le résultat suivant:

Mines vs Rocks model evaluation

Accuracy: 60.0 % Precision: 55.6 % Recall: 100.0 %

Ce résultat montre que:

-	Sur l'ensemble des données de test, 60.0% sont correctement classifiées.
-	Sur l'ensemble des données de test ayant effectivement rebondies sur des mines, 55.6% d'entre elles ont été prédites comme telles.
-Toutes les données de test qui ont été prédites comme ayant rebondies sur des mines ont effectivement rebondi sur des mines (100%).
