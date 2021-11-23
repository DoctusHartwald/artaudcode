#!/usr/bin/perl

print "Calcul d'un prix TTC\n";
#On initialise la valeur de notre taxe
$taxe = 0.196;
#On demande de rentrer un prix hors taxe
$pht = <stdin>;
#On enlève le caractère de retour chariot
chomp $pht;
#D'où la valeur ttc
$ttc = $pht+($pht*$taxe);
print "Le prix TTC est de : $ttc.\n";