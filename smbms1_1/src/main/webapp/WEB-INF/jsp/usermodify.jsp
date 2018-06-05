<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
        <fm:form id="userForm"  modelAttribute="user" name="userForm" method="post" action="${pageContext.request.contextPath }/user/modify">
			<input type="hidden" name="id" value="${user.id}"/>
			 <div>
                    <label for="userName">用户名称：</label>
                    <fm:input type="text" path="userName" id="userName" value="${user.userName }"/>
					<font color="red"></font>
             </div>
			 <div>
                    <label >用户性别：</label>
                    <fm:select path="gender" id="gender">
						<c:choose>
							<c:when test="${user.gender == 1 }">
								<option value="1" selected="selected">男</option>
					    		<option value="2">女</option>
							</c:when>
							<c:otherwise>
								<option value="1">男</option>
					    		<option value="2" selected="selected">女</option>
							</c:otherwise>
						</c:choose>
					 </fm:select>
             </div>
			 <div>
                    <label for="birthday">出生日期：</label>
                    <fm:input type="text" Class="Wdate" id="birthday" path="birthday" value="${user.birthday }"
					readonly="readonly" onclick="WdatePicker();"/>
                    <font color="red"></font>
              </div>
			
		       <div>
                    <label for="phone">用户电话：</label>
                    <fm:input type="text" path="phone" id="phone" value="${user.phone }"/>
                    <font color="red"></font>
               </div>
                <div>
                    <label for="address">用户地址：</label>
                    <fm:input type="text" path="address" id="address" value="${user.address }"/>
                </div>
				<div>
                    <label >用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<input type="hidden" value="${user.userRole }" id="rid" />
					<fm:select path="userRole" id="userRole"></fm:select>
        			<font color="red"></font>
                </div>
			 <div class="providerAddBtn">
                    <input type="button" name="save" id="save" value="保存" />
                    <input type="button" id="back" name="back" value="返回"/>
                </div>
            </fm:form>
        </div>
    </div>
</section>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/usermodify.js"></script>
