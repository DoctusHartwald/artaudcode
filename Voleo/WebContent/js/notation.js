<!-- 
StarOutUrl=		 '/Voleo/css/images/SunOut.gif';		//image par d�faut
StarOverUrl=	 '/Voleo/css/images/SunOver.gif';		//image d'une �toile s�lectionn�e
StarBaseId=		 'Document';				//id de base des �toiles
StarCompletionId='Star';
NbDoc=0;
NbStarTotal=			5;					//nombre d'�toiles

LgtStarBaseId=StarBaseId.lastIndexOf('');

function NotationSystem() {
	for(j=1;j<=NbDoc;j++){
		for (i=1;i<=NbStarTotal;i++) {
			var img	= document.getElementById(StarBaseId+j+StarCompletionId+i);
			
			img.alt			='Donner la note de '+i;
			//Texte au survol
			
			//img.src			=StarOutUrl;
			img.onmouseover	=function() {StarOver(this.id);};
			img.onmouseout	=function() {StarOut(this.id);};
		}
	}
}

function StarOver(Star) {
	NbStar=getNbStar(Star);
	j = getNbDocument(Star);
	for (i=1;i<=NbStar;i++)
		document.getElementById(StarBaseId+j+StarCompletionId+i).src=StarOverUrl;
	while(i<=NbStarTotal){
		document.getElementById(StarBaseId+j+StarCompletionId+i).src=StarOutUrl;
		i++
	}
}

function StarOut(Star) {
	NbStar=getNbStar(Star);
	j = getNbDocument(Star);
	for (i=1;i<=NbStar;i++){
		document.getElementById(StarBaseId+j+StarCompletionId+i).src=StarOutUrl;
	}
	while(i<=NbStarTotal){
		document.getElementById(StarBaseId+j+StarCompletionId+i).src=StarOutUrl;
		i++
	}
}

function getNbDocument(Star){
	return Star.substring(StarBaseId.length,Star.indexOf(StarCompletionId));
}

function getNbStar(Star) {
	SubStar = Star.substring(Star.indexOf(StarCompletionId),Star.length)
	NbStar=SubStar.slice(StarCompletionId.length);
	return(NbStar);
} 
-->