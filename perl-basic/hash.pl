#!/usr/bin/perl

print "Quelques URLs intéressantes\n";
#Les clés à gauche de la grosse flèche, les valeurs à droite de la grosse flèche
%urls = ("faq_perl"=>"http://perl.developpez.com/faq",
	"faq_linux"=>"http://linux.developpez.com/faq",
	"faq_java"=>"http://java.developpez.com/faq/java");

#Une boucle bien utile pour les listes associatives avec une chouette fonction
print "Listes des urls :\n";
#Une boucle intéressante pour récupérer les clés et les valeurs
while (($cle, $valeur) = each(%urls)){
	print $cle." a pour url ".$valeur."\n";
}
#Pour accéder à une valeur particulière si on connait sa clé
print "On ne retient quand même que l'url de la FAQ Perl qui est ".$urls{"faq_perl"}." :)\n";

#accès à un élément en fonction de sa clé