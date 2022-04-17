<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text.html; charset=UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
function allCheck(e) {
    if(e.target.checked) {
      document.querySelectorAll(".check_all_list").forEach(function(v, i) {
        v.checked = true;
      });
    } else {
      document.querySelectorAll(".check_all_list").forEach(function(v, i) {
        v.checked = false;
      });
    }
  }
  function checkAllList(e) {
    let checkCount = 0;
    document.querySelectorAll(".check_all_list").forEach(function(v, i) {
      if(v.checked === false){
        checkCount++;
      }
    });

    if(checkCount>0) {
      document.getElementById("allCheck").checked = false;
    } else if(checkCount === 0) {
      document.getElementById("allCheck").checked = true;
    }
  }
</script>
</head>
<body>


<ul class="checkbox_list">
      <li class="type_all_check">
        <div class="m_check_wrap">
          <input type="checkbox" id="allCheck" onclick="allCheck(event)">
          <label for="allCheck">All agree</label>
        </div>
      </li>
      <li>
        <div class="m_check_wrap">
          <input type="checkbox" class="check_all_list" id="agree1" onclick="checkAllList(event)">
          <label for="agree1">agree1</label>
        </div>
      </li>
      <li>
        <div class="m_check_wrap">
          <input type="checkbox" class="check_all_list" id="agree2" onclick="checkAllList(event)">
          <label for="agree2">agree2</label>
        </div>
      </li>
    </ul>

</body>
</html>