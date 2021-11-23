#!/usr/bin/perl

sub inverse{
	if ($_[0] == 0){
		print "Division par 0 impossible !\n";
	}
	else {
		$resultat = 1/$_[0];
		print "L'inverse de $_[0] est $resultat.\n";
	}
}

print "De quel nombre voulez-vous avoir l'inverse ?\n";
$nombre = <stdin>;
chomp $nombre;
inverse($nombre);