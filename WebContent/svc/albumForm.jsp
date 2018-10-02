<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="setting.jsp" %>
<script src="${project}script.js"></script>
<h2>albumForm</h2>
 <form name="albumForm" action="albumPro.go" method="post" enctype="multipart/form-data">
        <table> 
        	<tr>
                <td>
                	<input type="file" name="files" multiple="multiple"/>
                	<input type="hidden" name="tb_no" value="${tb_no}"/>
                </td>
            </tr>
            <tr>    
              <td><input type="submit" value="전송" /> </td>
            </tr>
        </table>      
  </form>