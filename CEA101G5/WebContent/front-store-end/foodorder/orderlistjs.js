$('window').ready(function(){
	
	let orderMenu= document.querySelectorAll('.orderMenu');
	let menu = document.querySelectorAll('.menu')
    for(let i=0;i<orderMenu.length;i++){
    	orderMenu[i].addEventListener('click',function(){
    		 $(menu[i]).slideToggle()
    	})
    }

})