#/usr/bin/perl

print "Des listes et des boucles !\n";
#Un tableau bien hétérogène !
@tablo = (1, 2, 3, "foo", 4, "bar");

#Et une boucle une ! # $valeur est une variable scalaire
foreach $valeur (@tablo){
	#Au passage, faisons une petite concaténation
	print $valeur."\t";
}
#Accès à une valeur particulière
print "\nEt si je veux la 4ème valeur du tableau ?\n";
print "La 4ème valeur du tableau est $tablo[3]\n";