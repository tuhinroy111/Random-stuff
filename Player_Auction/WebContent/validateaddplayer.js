/**
 * 
 */
function addplayer()
{
	var playername=document.getElementById("pname").value;
	var category=document.getElementById("category").value;
	var highscore=document.getElementById("hscore").value;
	var bestfigure=document.getElementById("bfigure").value;
	var teamname=document.getElementById("team").value;
	
	if(playername==null || playername=="")
		{
			alert("Playername field cannot be blank.");
			return false;
		}
	if(!(playername.match(/[a-zA-Z]+/)))
		{
			alert("Invalid player name, please use alphabets.");
			return false;
		}
	if(highscore==null || highscore=="")
	{
		alert("Highscore cannot be blank.");
		return false;
	}
	if((category=="Batsman")&&(highscore<50 || highscore>200))
		{
		 alert("Invalid highscore for batsman, please enter between 50 and 200.");
		 return false;
		}
	
	if(category=="Bowler" && bestfigure=="")
		{
			alert("Bestfigure cannot be blank for bowler.");
		}
	var pattern= new RegExp(/^([0-9]|[1][0])[/][0-9]+$/);
	if((category=="Bowler")&&(highscore<0 || !(pattern).test(bestfigure)))
		{
			alert("Invalid bowler, please check highscore or bestfigure");
			return false;
		}
	if((category=="Batsman")&&(highscore<0 || !(pattern).test(bestfigure)))
	{
		alert("Invalid batsman, please check highscore or bestfigure");
		return false;
	}
	if((category=="All-Rounder")&&(highscore<0 || !(pattern).test(bestfigure)))
	{
		alert("Invalid All-rounder, please check highscore or bestfigure");
		return false;
	}
	return true;
}