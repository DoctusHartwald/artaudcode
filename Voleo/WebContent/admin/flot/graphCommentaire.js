function graphCommentaire (commentaireGraphe) {
alert(commentaireGraphe);
    var d2 =commentaireGraphe;    
    $.plot($("#placeholder"), [
        {
            data: d2,
			lines: { show: true },
            points: { show: true }
        }
    ]);
}