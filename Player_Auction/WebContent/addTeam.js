/**
 * 
 */

function addTeam()
{
	var teamname=document.getElementById("tname").value;
	
	if(teamname==null || teamname=="")
	{
		alert("Teamname field cannot be blank.")
		return false;
	}
    if(!(teamname.match(/[a-zA-Z ]+/)))
	{
		alert("Invalid Team name, please use alphabets.");
		return false;
	}
return true;
	
}