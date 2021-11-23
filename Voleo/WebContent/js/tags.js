var idImageDelete = "deleteImage";

var tagNb = 0;

var idToRemove = new Array();

/*dojo.event.topic.subscribe("/before", function(data, type, request){
    if(type=="before"){
    	var listTags = document.getElementById("listTags");
    	var lesTagsAEnvoyer = document.getElementById("nomDuTag");
    	for(var i = 0; i < listTags.childNodes.length; i++){
    		nodeText = listTags.childNodes[i].firstChild;
    		if(nodeText != null)
    			lesTagsAEnvoyer.value += nodeText.nodeValue + ";";
    	}
    }
    //event: set event.cancel = true, to cancel request
    //widget: widget that published the topic
});*/

function updateTagsToSend(){
	var listTags = document.getElementById("listTags");
   	var lesTagsAEnvoyer = document.getElementById("nomDuTag");
   	
   	lesTagsAEnvoyer.value ="";
   	for(var i = 0; i < listTags.childNodes.length; i++){
   		nodeText = listTags.childNodes[i].firstChild;
   		if(nodeText != null)
   			lesTagsAEnvoyer.value += nodeText.nodeValue + ";";
   	}
}

function deleteTags(){
	for(var i = 0; i < idToRemove.length; i++){
		var noeud = document.getElementById(idToRemove[i]);
		noeud.parentNode.removeChild(noeud);
	}
	idToRemove = new Array();
	document.getElementById(idImageDelete).style.display="none";
	updateTagsToSend();
}

function beUnremovable(tag){
	tag.style.color = "";
	tag.setAttribute("onclick","beRemovable(this)");
	var idToRemoveTemp = new Array();
	for( var i = 0; i< idToRemove.length ; i++)
		if(idToRemove[i] != tag.id)
			idToRemoveTemp.push(idToRemove[i]);
	idToRemove = idToRemoveTemp;
	if(!idToRemove.length)
		document.getElementById(idImageDelete).style.display="none";
}

function beRemovable(tag){
	tag.style.color="red";
	idToRemove.push(tag.id);
	tag.setAttribute("onclick","beUnremovable(this)");
	if(idToRemove.length == 1)
		document.getElementById(idImageDelete).style.display="inline";
}

function createTags(tag){
	tagNb++;
	return '<span id="tagDuDoc'+tagNb+'" onclick="beRemovable(this)">' + tag + '</span>';
}

function exist(tag){
	var listTags = document.getElementById("listTags");
   	for(var i = 0; i < listTags.childNodes.length; i++){
   		nodeText = listTags.childNodes[i].firstChild;
   		if(nodeText != null)
   			if(tag == nodeText.nodeValue)
   				return 1;
   	}
   	return 0;
}

function checkComma(combobox){
	var listTags = document.getElementById("listTags");
	var text = combobox.textInputNode;
	lastCharacter = text.value.substring(text.value.length-1,text.value.length);
	if(lastCharacter == ";"){
		tag = text.value.substring(0,text.value.length-1);
		if(!exist(tag)){
			listTags.innerHTML += ' ' + createTags(tag);
			updateTagsToSend();
		}
		text.value="";
	}
}
