<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="project" value="/Travelers/svc/"/> <!-- o -->

<c:set var="page_main" value="메인 페이지"/>
<c:set var="page_input" value="회 원 가 입"/> 
<c:set var="page_confirm" value="아이디 중복확인"/>
<c:set var="page_login" value="로 그 인"/> <!-- o -->
<c:set var="page_delete" value="회 원 탈 퇴"/>
<c:set var="page_modify" value="회원 정보 수정"/>

<c:set var="msg_main" value="비회원이시면 회원가입을 해주세요"/>
<c:set var="msg_input" value="회원정보를 입력하세요"/> <!-- x -->
<c:set var="msg_confirm_x" value="는 사용할 수 없는 아이디입니다"/>
<c:set var="msg_confirm_o" value="는 사용할 수 있는 아이디입니다"/>
<c:set var="msg_login" value="로그인 정보를 입력하세요"/> <!-- x -->
<c:set var="msg_main_login" value="님 안녕하세요"/>
<c:set var="msg_passwdform" value="비밀번호를 다시 한 번 입력해주세요"/>
<c:set var="msg_modify" value="수정할 정보를 입력하세요"/>

<c:set var="str_id" value="아이디"/> <!-- o -->
<c:set var="str_passwd" value="비밀번호"/> <!-- o -->
<c:set var="str_nickname" value="닉네임"/>
<c:set var="str_gender" value="성별"/>
<c:set var="str_gender_f" value="여성"/>
<c:set var="str_gender_m" value="남성"/>
<c:set var="str_email" value="이메일"/>
<c:set var="str_reg_date" value="가입일자"/>

<c:set var="str_manual" value="직접입력"/>
<c:set var="str_name" value="이름"/>
<c:set var="str_jumin" value="주민등록번호"/>
<c:set var="str_tel" value="전화번호"/>

<c:set var="btn_login" value="로그인"/> <!-- o -->
<c:set var="btn_cancel" value="취소"/>
<c:set var="btn_join" value="가입하기"/>
<c:set var="btn_join_cancel" value="가입취소"/>
<c:set var="btn_confirm" value="중복확인"/>
<c:set var="btn_confirm_cancel" value="확인취소"/>
<c:set var="btn_confirm_smtp" value="본인인증"/>
<c:set var="btn_ok" value="확인"/>
<c:set var="btn_modify" value="정보수정"/>
<c:set var="btn_delete" value="회원탈퇴"/>
<c:set var="btn_logout" value="로그아웃"/>
<c:set var="btn_mod" value="수정"/>
<c:set var="btn_mod_cancel" value="수정취소"/>
<c:set var="btn_del" value="탈퇴"/>
<c:set var="btn_del_cancel" value="탈퇴취소"/>
         
