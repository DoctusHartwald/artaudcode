#!/usr/bin/perl

print "Quelques URLs int�ressantes\n";
#Les cl�s � gauche de la grosse fl�che, les valeurs � droite de la grosse fl�che
%urls = ("faq_perl"=>"http://perl.developpez.com/faq",
	"faq_linux"=>"http://linux.developpez.com/faq",
	"faq_java"=>"http://java.developpez.com/faq/java");

#Une boucle bien utile pour les listes associatives avec une chouette fonction
print "Listes des urls :\n";
#Une boucle int�ressante pour r�cup�rer les cl�s et les valeurs
while (($cle, $valeur) = each(%urls)){
	print $cle." a pour url ".$valeur."\n";
}
#Pour acc�der � une valeur particuli�re si on connait sa cl�
print "On ne retient quand m�me que l'url de la FAQ Perl qui est ".$urls{"faq_perl"}." :)\n";

#acc�s � un �l�ment en fonction de sa cl�