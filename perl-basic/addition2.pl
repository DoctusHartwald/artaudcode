#!/usr/bin/perl

print "Calcul d'un prix TTC\n";
#On initialise la valeur de notre taxe
$taxe = 0.196;
#On demande de rentrer un prix hors taxe
$pht = <stdin>;
#On enl�ve le caract�re de retour chariot
chomp $pht;
#D'o� la valeur ttc
$ttc = $pht+($pht*$taxe);
print "Le prix TTC est de : $ttc.\n";