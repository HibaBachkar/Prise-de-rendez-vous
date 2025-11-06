-- Script SQL pour réparer l'historique Flyway
-- Exécutez ce script dans votre base de données MySQL

USE rdv;

-- Voir l'historique actuel
SELECT * FROM flyway_schema_history;

-- Réparer le checksum de la migration V1
-- Le checksum 1560186028 correspond au fichier local actuel
UPDATE flyway_schema_history 
SET checksum = 1560186028 
WHERE version = '1';

-- Vérifier la mise à jour
SELECT * FROM flyway_schema_history WHERE version = '1';

-- Si vous préférez réinitialiser complètement (supprime l'historique)
-- DÉCOMMENTEZ les lignes suivantes :
-- DROP TABLE IF EXISTS flyway_schema_history;
-- Note: Cela forcera Flyway à réappliquer toutes les migrations au prochain démarrage

