$(document).ready(function() { 
    i=1;
    var flag1 = 0;
    var flag2 = 0;
    
    $("#controlbuttonright").click(function(){
        changeImgAdd();
    });
    $("#controlbuttonleft").click(function(){
        changeImgSubst();
    });
    
    function changeImgAdd(){
        i++;
        document.getElementById("imagchange").src="images/imag"+i+".jpg";
        return i; 
    }
    function changeImgSubst(){
        i--;
        document.getElementById("imagchange").src="images/imag"+i+".jpg";
        return i; 
    }
    
    
    function ergodic(str){
        
        
        if(flag1<1&&flag2<1){
            //alert("x!=1");
            for(j=1;j<20;j++){
            var bigImg= document.createElement("img");
            bigImg.src ="images/imag"+j+".jpg";
            bigImg.width="200";
            bigImg.height="200";
            var myp = document.getElementById(str);
            myp.appendChild(bigImg);
            };
            flag1++;
        }else if(flag1<1||flag2<1){
            for(j=1;j<20;j++){
                var bigImg= document.createElement("img");
                bigImg.src ="images/imag"+j+".jpg";
                bigImg.width="200";
                bigImg.height="200";
                var myp = document.getElementById(str);
                myp.appendChild(bigImg);
                };
                flag2++;
        }else{
            return;
        }
            
            
        
    }
    
    document.onkeydown= function (e) {
        var theEvent = window.event || e; 
        var code = theEvent.keyCode || theEvent.which; 
        if (code == 37) { 
            $("#controlbuttonleft").click(); 
        }else if(code == 39){
            $("#controlbuttonright").click();
        }
        
        }

    

    $("#tab_bar").show();
    //$("#tab_bar").addClass("end");
    //$("#DialerUp").addClass("end");
    //$("#ContactListUp").addClass("end");
    //$("#AddContactUp").addClass("end");   
    $("#imageView1").show(); 
    $("#imageView2").hide();
    $("#imageView3").hide();

    $("#firstMethod").click(function() { 
    // if($(this).hasClass("end")){
    //     $(this).removeClass("end");
    //     $(this).addClass("start");
    //     $("#ContactListUp").addClass("end");
    //     $("#AddContactUp").addClass("end");
    //    // }else{
    //     $("#ContactListUp").addClass("end");
    //     $("#AddContactUp").addClass("end");
    //     //};
   
    $("#tab_bar").show();
    $("#imageView1").show();
    $("#imageView2").hide();
    $("#imageView3").hide(); 
});      
    $("#secondMethod").click(function() {
         
    // if($(this).hasClass("end")){
    //     $(this).removeClass("end");
    //     $(this).addClass("start");
    //     $("#DialerUp").addClass("end");
    //     $("#AddContactUp").addClass("end");
    // }else{
    //     $("#DialerUp").addClass("end");
    //     $("#AddContactUp").addClass("end");
    //     };   
   
    $("#tab_bar").show(); 
    ergodic("imageshow2");
    $("#imageView2").show();
    $("#imageView1").hide(); 
    $("#imageView3").hide();
    
}); 
    $("#thirdMethod").click(function() { 
    // if($(this).hasClass("end")){
    //     $(this).removeClass("end");
    //     $(this).addClass("start");
    //     $("#ContactListUp").addClass("end");
    //     $("#DialerUp").addClass("end");
    // }else{
    //     $("#ContactListUp").addClass("end");
    //     $("#DialerUp").addClass("end");   
    //     };     
     a=0;
    $("#tab_bar").show(); 
    ergodic("unit");
    $("#imageView3").show();
    $("#imageView1").hide(); 
    $("#imageView2").hide();
 }); 
   
});              
    
           