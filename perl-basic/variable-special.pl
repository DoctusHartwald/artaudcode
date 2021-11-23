#!/usr/bin/perl

@liste = (0,1,2,3,4,5,6,7,8,9,10);
print "Table de multiplication de 2\n";
foreach (@liste){ #Variante :foreach $_ (@liste){
	print "$_*2 = ".($_*2)."\n";
}


#Memo
#contient tout ce qui est en cours d'une boucle
# ou elle est la variable par défaut de nombreuses fonctions. 