#!/usr/bin/perl

print "Rentres un nom de fichier :\n";
$fichier = <stdin>;
chomp $fichier;
if (-f $fichier){
	print "$fichier existe.\n";
}
else {
	print "$!\n";
}

#Memo variable $! sert ainsi à enregistrer l'état de votre erreur 