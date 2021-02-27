<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous">
</script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberEnroll.css">

<section id="enroll-container">
	<h2>회원 가입 정보 입력</h2>
	<form action="" method="POST">
	    <table>
	        <tr>
	            <th>아이디<sup>*</sup></th>
	            <td>
	                <input type="text" name="memberId" id="member_Id" placeholder="3글자 이상" required>
	                <input type="button" value="중복확인" onclick="memberIdDuplicate()">
                    <input type="hidden" id="idValid" value="0">
                </td>
           	</tr>
            <tr>
                <th>비밀번호<sup>*</sup></th>
                <td>
                    <input type="password" name="password" id="password" placeholder="8자 이상" required>
                </td>
            </tr>
            <tr>
                <th>비밀번호 확인<sup>*</sup></th>
                <td>
                    <input type="password" name="password" id="password_checked" required>
                </td>
            </tr>
            <tr>
                <th>이름<sup>*</sup></th>
                <td>
                    <input type="text" name="name" id="name" required>
                </td>
            </tr>
            <tr>
                <th>주민번호<sup>*</sup></th>
                <td>
                    <input type="text" name="regi_number" id="regi_number" placeholder="생년월일" required maxlength="6"> -
                    <input type="password" name="regi_number" id="regi_number2" maxlength="7">
                </td>
            </tr>
            <tr>
                <th>이메일<sup>*</sup></th>
                <td>
                    <input type="email" name="email" id="email" placeholder="abc@naver.com" required>
                </td>
            </tr>
            <tr>
                <th>이메일 수신 여부</th>
                <td>
                    <div class="emailCheked-container">
                        <label for="">Y</label><input type="radio" name="email_checked" value="Y" checked="checked">
                        <label for="">N</label><input type="radio" name="email_checked" value="N">
                    </div>
                </td>
            </tr>
            <tr>
                <th>휴대폰 번호<sup>*</sup></th>
                <td>
                    <input type="tel" name="mobile" id="mobile" placeholder="-없이" required>
                </td>
            </tr>
            <tr>
                <th>자택 번호&nbsp;</th>
                <td>
                    <input type="tel" name="mobile" id="mobile" placeholder="-없이">
                </td>
            </tr>
            <tr>
                <th>우편 번호&nbsp;</th>
                <td>
                    <input type="text" name="zipCode" id="zipCode" class="postcodify_postcode5">
                    <input type="button" id="zip_code_search" value="우편번호">
                </td>
            </tr>
            <tr>
                <th>자택 주소&nbsp;</th>
                <td>
                    <input type="text" name="address" id="address" class="postcodify_address">
                </td>
            </tr>
            <tr>
                <th>상세 주소&nbsp;</th>
                <td>
                    <input type="text" name="address_detail" id="address_detail" class="postcodify_details">
                </td>
            </tr>
        </table>
    </form>
</section>
