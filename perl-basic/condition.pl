#!/usr/bin/perl
while(1){	
	print "Rentres une ville :\n";
	$ville = <stdin>;
	chomp $ville;
	if ($ville eq "Paris") {
		print $ville." rentre dans une bouteille !\n";
	}
	else{
		print "Votre ville est : ".$ville
	}
}