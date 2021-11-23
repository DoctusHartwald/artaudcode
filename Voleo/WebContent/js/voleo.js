var size=0;	//0 si fenêtre réduite, 1 si fenêtre agrandie

function redimmensionner()	{
	if(size == 1)
		reduire(750,578);
	else
		agrandir(300,128);
}

function agrandir(new_width,new_height)	{
	if(new_width < 750)	{
		new_width = new_width + 40;
		document.getElementById("perso").style.width = new_width + "px";
	}
	if(new_height < 578)	{
		new_height = new_height + 40;
		document.getElementById("perso").style.height = new_height + "px";
	}
	else	{
		size = 1;
		return 0;	
	}
	setTimeout(function(){agrandir(new_width,new_height)}, 10);
}

function reduire(new_width,new_height)	{
	if(new_width > 300)	{
		new_width = new_width - 30;
		document.getElementById("perso").style.width = new_width + "px";
	}
	if(new_height > 158)	{
		new_height = new_height - 30;
		document.getElementById("perso").style.height = new_height + "px";
	}
	else	{
		size = 0;
		return 0;	
	}
	setTimeout(function(){reduire(new_width,new_height)}, 10);
}
	

