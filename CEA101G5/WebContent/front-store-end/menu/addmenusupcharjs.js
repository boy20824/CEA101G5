$('document').ready(function(){
    let addDetail =document.querySelectorAll(".menuCharDetailTitle p:last-child")
    let menuCharAdd =document.querySelectorAll(".menuCharAdd")
    for(let i=0;i<addDetail.length;i++){
        // 增加選項
        addDetail[i].addEventListener('click',function(){
            let put = document.createElement('div');
            put.setAttribute('class','put')
            let addone =document.createElement('input');
            addone.setAttribute('name','menuChar')
            addone.setAttribute('type','text')
            let icon =document.createElement('img');
            icon.setAttribute('src','/CEA101G5/front-store-end/menu/1.png')
            put.append(icon)
            put.append(addone)
            menuCharAdd[i].append(put);


            // 刪除選項
            let icons = document.querySelectorAll('.menuCharAdd .put img');
            for(let i=0;i<icons.length;i++){
                icons[i].addEventListener('click',function(e){
                    e.target.nextElementSibling.remove();
                    e.target.remove();
                })
            }
        })

    }
//    刪除選項
    let icons = document.querySelectorAll('.menuCharAdd .put img');
    for(let i=0;i<icons.length;i++){
        icons[i].addEventListener('click',function(e){
        e.target.nextElementSibling.remove();
        e.target.remove();
    })
}
    $('.fas').click(function(){
    	$('.menuChar').hide()
    })
    
})