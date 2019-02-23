$(document).ready(function() { 
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
     
    $("#tab_bar").show(); 
    $("#imageView3").show();
    $("#imageView1").hide(); 
    $("#imageView2").hide();
 }); 
   
});              
    
           