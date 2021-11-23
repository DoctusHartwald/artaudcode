#!/usr/bin/perl

print "Rentres un chiffre pour connaître son inverse :\n";
$nombre = <stdin>;
chomp $nombre;
unless($nombre == 0){
	$resultat = 1/$nombre;
	print "L'inverse de ".$nombre." est ".$resultat."\n";
}
else {
	print "La division par 0 est impossible !";
}

#Memo 
#Chiane de caractere :eq (pour équivalent) 
#unless vérifie si la condition est fausse.