$('document').ready(function(){
    let cmt = document.querySelectorAll('.cmt');
    let box = document.querySelectorAll('.box');
    
    for(let i=0;i<cmt.length;i++){
    	 cmt[i].addEventListener('click',function(){
    	        box[i].style.display='block';
    	    })
    }
    let cancel = document.querySelectorAll('.cancel');
    for(let i=0;i<cancel.length;i++){
    	cancel[i].addEventListener('click',function(){
    		box[i].style.display='none';
    	})
    }
})