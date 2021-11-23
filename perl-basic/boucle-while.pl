#!/usr/bin/perl

print "Avec While ----";
print "Table de multiplication de 2\n";
$i = 0;
while($i<=10){
	print "$i*2 = ".($i*2)."\n";
	$i++;
}


#A Savoir 
## La pré-incrémentation : ++$i; où la valeur sera d'abord incrémentée.
# La post-incrémentation : $i++; où la valeur sera après incrémentée.