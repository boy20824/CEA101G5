$('document').ready(function(){

    let storeId =document.querySelector('.storeId').value;

    let list =document.querySelector('.show');
// 餐點搜尋
    $.ajax({
        url:'/CEA101G5//menu/MenuServlet.do?menuChar=主餐'+'&storeId='+storeId+'&action=menuAjax',
                    type : "GET",
                    dataType : "json",
                    success : function(data) { 
                        show(data)
                    }
                })
                  
        function show(data) {
    	let str="";
            for(mydata in data){
            	if(data[mydata].menuStatus==0 && data[mydata].menuSellStatus==1){
            		  str+=`<div class="list"><img src="/CEA101G5/menu/MenuServlet.do?menuId=`+data[mydata].menuId+`&action=getOnePicture" />
                          <div class="detail">
                            <ul>
                              <li class="detailTitle"><p>`+data[mydata].menuName+`</p></li>
                              <li class="word">
                                <p>`+
                                data[mydata].menuDetail
                                +`</p>
                              </li>
                              <li class="prize">
                                <span>`+data[mydata].menuPrice+`</span>
                                <img src="/CEA101G5/front-customer-end/menu/img/ICON/dollar-sign-solid.svg" alt="" />
                              </li>
                              <li class="bth">
                              <input type="button" value="餐店備註">
                              <input type="button" value="-" class="del" style="font-size: 20px;" />
                              <span name="quantity">1</span>
                              <input type="button" value="+" class="addd"style="font-size: 20px;" />
                              <input type="button" value="加入購物車"  class="add"/>
                              </li>
                            </ul>
                          </div> </div>`
                                
            	}
            
            }
        
            
            list.innerHTML=str;
            // 註冊餐點的加入購物車
            
            // 取得餐點編號存入陣列(依照順序加入)
            let menuNumber =[];
            for(mydata in data){
            	if(data[mydata].menuStatus==0 && data[mydata].menuSellStatus==1){
            		menuNumber.push(mydata)
            	}
    		}
            
            let add =document.querySelectorAll(".add")// 註冊每個餐點的按鈕
          
            let memberId =document.querySelector(".memberId") 
            let quantity =document.querySelectorAll('span[name="quantity"]')
            // 點擊按鈕後觸發加入購物車事件
            
            for(let i=0;i<add.length;i++){
            	add[i].addEventListener('click', function(){
         
            		$.ajax({
            			url:'/CEA101G5//menu/MenuServlet.do?menuId='+data[menuNumber[i]].menuId+'&quantity='+quantity[i].innerText+'&action=add',
            			type : "GET",
            			dataType : "json",
            			success : function(data) { 
                    	showCar(data);
                    	quantity[i].innerText=1;
            			}
            		})     		         		
            	})
            	
            }     
           
            let del =document.querySelectorAll('.del')
            let addd =document.querySelectorAll('.addd')
             for(let i=0;i<quantity.length;i++){
            	 del[i].addEventListener('click',function(){
            		 var num=parseInt(quantity[i].innerText)-1;
                  	if(num<1){
                  		quantity[i].innerText=1;
                  	}else{
                  		quantity[i].innerText=num;
                  	}
            	 })
            	addd[i].addEventListener('click',function(){
            		var num=parseInt(quantity[i].innerText)+1;
                 	quantity[i].innerText=num;
            	})
                 
             }
            
        }
    	
    
    
    
    // 餐點分類點擊顏色轉化事件並搭配餐點切換事件
    let menuChar = document.querySelectorAll('.menuChar')
    for(let i=0;i<menuChar.length;i++){
      menuChar[i].addEventListener('click', function(){
    	  for(let i=0;i<menuChar.length;i++){
    		  menuChar[i].style.color="black";
    	  }
        menuChar1 = menuChar[i].innerText;
        menuChar[i].style.color="orange";
        $.ajax({
            url:'/CEA101G5//menu/MenuServlet.do?menuChar='+menuChar1+'&storeId='+storeId+'&action=menuAjax',
            type : "GET",
            dataType : "json",
            success : function(data) { 
            show(data)    
             }
         })
      })
    }

    // 購物車添加
    function showCar(data){
    	
    	var dom = document.getElementById(data.menuId);
    	if(dom != null) {
    		dom.querySelector('.item-quantity').innerText = "Quantity:"+data.quantity;
    	} else {
    		let str;
        	let div = document.createElement('div');
        	str=`<div id="`+data.menuId+`" class="carlist">
    		<ul class="shopping-cart-items">
    		<li class="clearfix">
    		<img src="/CEA101G5/menu/MenuServlet.do?menuId=`+data.menuId+`&action=getOnePicture" alt="item1" /> 
    		<span class="item-name">`+data.menuName+`</span>
    		<span class="item-price">$`+data.menuPrice+`</span>
    		<span class="item-quantity">Quantity:`+data.quantity+`</span>
    		<span class="item-remove">移除</span>
    		</li>
		    </ul>
		    </div>
		    </div>`
    		
	        div.innerHTML=str;
	        document.querySelector(".forCar").prepend(div);
    	}

    	
    }
 
    
// 點擊購物車按鈕
    $('.shopCard').click( function(){
//    	清除不同餐廳要先清空購物車
    	let storeId = document.querySelector('.storeId');
    	$.ajax({
			url:'/CEA101G5//menu/MenuServlet.do?action=clear&storeId='+storeId.value,
			type : "GET",
			dataType : "json",
			success : function(data) { 
			}
		})     		
    	 // 點擊購物車移除觸發刪除事件
        let remove = document.querySelectorAll('.item-remove');
        let getMenuId =document.querySelectorAll('.carlist')
        let badge=document.querySelector('.badge');
        
        for(let i=0;i<remove.length;i++){
        	remove[i].addEventListener('click', function(){
            	$.ajax({
                     url:'/CEA101G5//menu/MenuServlet.do?menuId='+getMenuId[i].getAttribute('id')+'&action=deletCar',
                     type : "GET",
                     success : function(data) { 
                    	 getMenuId[i].remove();
//                       改變購物車裡面的清單數量
                    	let remove = document.querySelectorAll('.item-remove');
                 		badge.innerText=remove.length;
//                      改變總金額
                        let money=document.querySelector('.main-color-text');
                        let price=document.querySelectorAll('.item-price');
                        let quantity=document.querySelectorAll('.item-quantity');
                        let total = 0;
                        for(let i=0;i<price.length;i++){
                        	
                        	let intQuantity = quantity[i].innerText.substring(9,10);
                        	let intPrice = price[i].innerText.split("$")[1]
                        	total+=(intQuantity*intPrice)
                        }
                        
                        money.innerText="$"+total;
                        
                         }
                     })   		
            	})	
        }
//        改變購物車裡面的清單數量
        if(remove){
        	badge.innerText=remove.length; 
        }
       
        
//        改變總金額
        let money=document.querySelector('.main-color-text');
        let price=document.querySelectorAll('.item-price');
        let quantity=document.querySelectorAll('.item-quantity');
        let total = 0;
        for(let i=0;i<price.length;i++){
        	
        	let intQuantity = quantity[i].innerText.substring(9,10);
        	let intPrice = price[i].innerText.split("$")[1]
        	total+=(intQuantity*intPrice)
        }
        
        money.innerText="$"+total;

    })
    
   
    
    
    
})