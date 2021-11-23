<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voleo</title>
<link rel="stylesheet" type="text/css" href="css/voleo.css" />
<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />
<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>

<link href="admin/flot/examples/layout.css" rel="stylesheet"
    type="text/css"></link>

<!--[if IE]><script language="javascript" type="text/javascript" src="admin/flot/excanvas.pack.js"></script><![endif]-->
<script language="javascript" type="text/javascript"
    src="admin/flot/jquery.js"></script>
<script language="javascript" type="text/javascript"
    src="admin/flot/jquery.flot.js"></script>

<style>
.small_show {
    font-size: 9px;
    font: "Arial", "Lucida Grande", Verdana, Sans-Serif;
}
</style>

</head>
<body>
		<div id="logo" onclick="window.location.href='index.action'"></div>
		<div id="perso">
			<!-- Bordures du cadre "perso" -->
			<div class="bord-bas-droite"></div>
			<div class="bord-bas-gauche"></div>
			<div class="bord-haut-droite"></div>
			<div class="cote-bas"></div>
			<div class="cote-droite"></div>
			<div id="perso_in">
				<s:action name="loginForm" executeResult="true" />
			</div>
		</div>
		<p class="titre">Voleo, le site de partage de connaissances</p>
		<s:div id="barreNavigationTags" cssClass="pres_titre">
			<s:action name="listSelectedTag" executeResult="true" />
		</s:div>
		<div id="gauche">
			<div class="pres_barre_verticale_gauche"></div>
			<div class="pres_barre_verticale_droite"></div>
			<div class="pres_titre">Rechercher</div>
			<div class="rechercher">
				<form id="searchForm" action="search.action" >
					<s:textfield id="keywords" size="13" name="request.keywords"/>
					<input type="submit" value="Rechercher"/>
				</form>
				<a href="searchForm.action">Recherche Avancée</a>
			</div>
			<div class="pres_titre">Liste des tags</div>
			<div class="liste_tags">
				<s:action name="listAvailableTag" executeResult="true" />
				<!--<s:iterator value="tags">
				<s:property value="name"/>(<s:property value="documentsCount"/>)
				<br />
				</s:iterator>-->
			</div>
		</div>
		<div id="droite">
			<div class="pres_barre_verticale_gauche"></div>
			<div class="pres_barre_verticale_droite"></div>
			<div class="pres_titre">Mon espace Perso</div>
			<div class="liste_derniers_articles">
				<s:action name="getEspacePerso" executeResult="true" />
			</div>
			<div class="pres_titre">Derniers Articles</div>
			<div class="liste_derniers_articles">                
				<s:action name="lastArticles" executeResult="true" />
			</div>
		</div>
<div id="contenu">
<div class="pres_titre">Avancement du site</div>
<h2>Liste de tous les Utilisateurs</h2>


<div id="placeholder" style="width:600px;height:300px;"></div>
    <p id="choices">Show:</p>
<fieldset>   
<legend><b><img src="/Voleo/admin/images/graphique.gif" alt="Graphiquel" />
Cliquez sur le Graphique</b></legend>
<table>
<tr><td>
<img src="/Voleo/admin/images/graph.png" width="30" height="30" alt="images" />Résultat : <br/>
<span id="result" style='color: #8806CE'></span>
</td></tr>
</table>
</fieldset>   
                   

<!-- Set Value data  -->
<s:property value="adminCountForum" /> <br />
<s:set name="data" value="adminCountForum" />

Afficher les Graphes :<br />

<s:submit label="Afficher graphique"
          onclick="javascript:graphCommentaire(%{data})" value="Afficher le graphique"/>
          <br />
          <!--
Graphe Document :   
<s:property value="adminCountDocument" /> <br />
<s:set name="data2" value="adminCountDocument" />
<s:submit label="Afficher graphique"
          onclick="javascript:graphCommentaire(%{data2},%{data})" />
          <br />-->

<script id="source" language="javascript" type="text/javascript">
/* $.plot(placeholder, data, options);*/
function  graphCommentaire(data) {
     var dataForum = data;
    var datasets = {
        "Message sur le forum": {
            label: "Nombre de messages sur le Forum",
            data : dataForum
        }
    };

    // hard-code color indices to prevent them from shifting as
    // countries are turned on/off
    var i = 0;
    $.each(datasets, function(key, val) {
        val.color = i;
        ++i;
    });
   
    // insert checkboxes
    var choiceContainer = $("#choices");
    $.each(datasets, function(key, val) {
        choiceContainer.append('<br/><input type="checkbox" name="' + key +
                               '" checked="checked" >' + val.label + '</input>');
    });
   
    //Appel de Fonction
    choiceContainer.find("input").click(plotAccordingToChoices);

   
    function plotAccordingToChoices() {
        var data = [];
          var options = {
          lines: { show: true ,fill: false},
          points: { show: true },
          colors: ["#bb0000", "#dba255", "#919733"],
          grid: { clickable: true },
          xaxis: {
                    mode: "time",
                  minTickSize: [1, "month"],
                  timeformat: "%d %b %y",
                    monthName: ["jan", "feb", "mar", "apr", "mai", "jun", "jul", "aug", "sep", "okt", "nov", "dec"]
           }
          };
        var options2 = {
        grid: { clickable: true }
        }
       
        choiceContainer.find("input:checked").each(function () {
            var key = $(this).attr("name");
            if (key && datasets[key])
                data.push(datasets[key]); //met les données dans le data
        });
       
        if (data.length > 0)
            //Plot Graphique
            $.plot($("#placeholder"),data,options);
           
            //Partie Clickable
            $("#placeholder").bind("plotclick", function (e, pos) {
                // the values are in pos.x and pos.y
                 var temps = new Date();
                 var absolu_ensuite = pos.x ;
                 temps.setTime(absolu_ensuite);
                 var mois = temps.getMonth() + 1;
                  var jour = temps.getDate();
                  var annee = temps.getFullYear();
                  
                    $("#result").text( jour + "/" + mois + "/" + annee+  ", " + pos.y.toFixed(2)+" TimeStamp Value : "+pos.x.toFixed(2) );
            });
           
    }
    plotAccordingToChoices();
}

</script> <br />
<br />


Resultat List Forum Count : <br/>
<s:property value="adminCountForum" />
<br/>
Resultat List Video : <br/>
<s:property value="adminCountVideo" />
<br/>
Resultat List IMAGE : <br/>
<s:property value="adminCountImage" />
<br/>
Resultat List Document : <br/>
<s:property value="adminCountDocument" />

 <br/> <br/>
</div>
<!-- Fin div Contenu  -->
</body>
</html>