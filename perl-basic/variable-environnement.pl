#!/usr/bin/perl

print "Liste des informations de votre syst�me :\n";
while (($cle, $valeur) = each (%ENV)){
	print "$cle a comme valeur $valeur.\n";
}