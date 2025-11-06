-- ============================================
-- SOLUTION DÉFINITIVE POUR RÉPARER FLYWAY
-- ============================================
-- Exécutez ce script dans MySQL pour réparer complètement Flyway

USE rdv;

-- Option 1 : Réparer le checksum (si vous voulez garder l'historique)
UPDATE flyway_schema_history 
SET checksum = 1560186028 
WHERE version = '1';

-- Vérifier la mise à jour
SELECT version, description, type, script, checksum, installed_on 
FROM flyway_schema_history 
WHERE version = '1';

-- ============================================
-- Option 2 : Réinitialiser complètement Flyway
-- (DÉCOMMENTEZ les lignes suivantes si Option 1 ne fonctionne pas)
-- ============================================

-- DROP TABLE IF EXISTS flyway_schema_history;
-- Note: Après avoir exécuté cette commande, redémarrez le service
-- Flyway recréera automatiquement la table et réappliquera toutes les migrations

-- ============================================
-- Option 3 : Supprimer seulement la migration V1 problématique
-- (Si vous voulez garder les autres migrations)
-- ============================================

-- DELETE FROM flyway_schema_history WHERE version = '1';
-- Note: Après cette commande, Flyway réappliquera V1 au prochain démarrage

