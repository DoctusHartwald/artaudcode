#/usr/bin/perl

print "Des listes et des boucles !\n";
#Un tableau bien h�t�rog�ne !
@tablo = (1, 2, 3, "foo", 4, "bar");

#Et une boucle une ! # $valeur est une variable scalaire
foreach $valeur (@tablo){
	#Au passage, faisons une petite concat�nation
	print $valeur."\t";
}
#Acc�s � une valeur particuli�re
print "\nEt si je veux la 4�me valeur du tableau ?\n";
print "La 4�me valeur du tableau est $tablo[3]\n";