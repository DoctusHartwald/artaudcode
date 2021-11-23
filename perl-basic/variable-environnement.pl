#!/usr/bin/perl

print "Liste des informations de votre système :\n";
while (($cle, $valeur) = each (%ENV)){
	print "$cle a comme valeur $valeur.\n";
}