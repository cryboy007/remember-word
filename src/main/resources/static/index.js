function clearHead() {
  //$("#head").hide();
}

function subscribe() {
  let email = $("input[name='email']").val();
  let regex = /^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/
  if (email != null ) {
    //记录邮件
    if (regex.test(email)) {
      
    }else {
       alert("邮箱格式不符合")
    }
  }
}