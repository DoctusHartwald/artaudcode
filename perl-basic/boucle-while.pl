#!/usr/bin/perl

print "Avec While ----";
print "Table de multiplication de 2\n";
$i = 0;
while($i<=10){
	print "$i*2 = ".($i*2)."\n";
	$i++;
}


#A Savoir 
## La pr�-incr�mentation : ++$i; o� la valeur sera d'abord incr�ment�e.
# La post-incr�mentation : $i++; o� la valeur sera apr�s incr�ment�e.