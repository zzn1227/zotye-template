

function isWeakPassword(password)  
{  
 	if(!isLengthOK(password,8)) {
 	 return "密码长度必须不小于8位!";
 	}
 	if(isAllNumeric(password)) {
	 return "密码不能全为数字!";
	} 
	if(isAllCharacter(password)) {
	    return "密码不能全为字符!";
	} 
    
}

	//判断正整数  
function isLengthOK(password,minLength)  
{  
   return password.length>=minLength; 
}
	//判断正整数  
function isAllNumeric(password)  
{  
     var re =/^[0-9]*$/;  
     if (!re.test(password))  
    {  
        return false;  
     }  
      return true; 
}  
	//是否是所有字符 
function isAllCharacter(password)  
{  
     var re =/^[a-zA-Z]*$/;  
     if (!re.test(password))  
    {  
        return false;  
     }  
      return true; 
}