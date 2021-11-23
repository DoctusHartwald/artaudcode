#!/usr/bin/perl

print "Rentres un chiffre pour connaître son inverse :\n";
$nombre = <stdin>;
chomp $nombre;
if($nombre == 0){
	print "La division par 0 est impossible !";
}
else {
	$resultat = 1/$nombre;
	print "L'inverse de ".$nombre." est ".$resultat."\n";
}