#!/usr/bin/perl
#les variables scalaires, les listes et les tables associatives


print "Calcul d'un prix TTC\n";
#On initialise la valeur de notre taxe
$taxe = 0.196;
#On fixe un prix hors taxe à 100
$pht = 100;
#D'où la valeur ttc
$ttc = $pht+($pht*$taxe);
print "Le prix TTC est de : $ttc.\n";