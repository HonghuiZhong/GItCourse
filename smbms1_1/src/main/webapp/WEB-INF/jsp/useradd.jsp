<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <%--文件上传,enctype="multipart/form-data",method="post"--%>
            <fm:form id="userForm" modelAttribute="user" name="userForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/user/save">

                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userCode">用户编码：</label>
                    <fm:input type="text" name="userCode" id="userCode" value="" path="userCode"/>
					<!-- 放置提示信息 -->
					<font color="red"></font>
                    <fm:errors path="userCode" style="color:red"/>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <fm:input type="text"  id="userName" value="" path="userName"/>
					<font color="red"></font> <fm:errors path="userName" style="color:red"/>
                </div>
                <div>
                    <label for="userPassword">用户密码：</label>
                    <fm:input type="password"  id="userPassword" value="" path="userPassword"/>
					<font color="red"></font>
                    <fm:errors path="userPassword" style="color:red"/>
                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="password" name="ruserPassword" id="ruserPassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label >用户性别：</label>
					<fm:select  id="gender" path="gender">
					    <option value="1" selected="selected">男</option>
					    <option value="2">女</option>
					 </fm:select>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <fm:input type="text" Class="Wdate" id="birthday" path="birthday"
					readonly="readonly" onclick="WdatePicker();"/>
					<font color="red"></font>
                    <fm:errors path="birthday" style="color:red"/>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <fm:input type="text" path="phone" id="phone" value=""/>
					<font color="red"></font>
                    <fm:errors path="phone" style="color:red"/>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                   <fm:input path="address" id="address"  value="" />
                    <fm:errors path="address" style="color:red"/>
                </div>
                <div>
                    <label >用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<fm:select path="userRole" id="userRole"></fm:select>
	        		<font color="red"></font>
                    <fm:errors path="userRole" style="color:red"/>
                </div>

                <div>
                    <label >工作照：</label>
                    <!-- 列出所有的角色分类 -->
                    <input type="file" name="pic" ></input>
                    <font color="red"></font>
                </div>


                <div class="providerAddBtn">
                    <input type="button" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </fm:form>
        </div>
</div>
</section>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/useradd.js"></script>
