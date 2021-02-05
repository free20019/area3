<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table id="inbox-table" class="table table-striped table-hover">
<tr id="msg1" class="unread">
			<td class="inbox-data-from hidden-xs hidden-sm">
				<div>
				车号
				</div>
			</td>
			<td class="inbox-data-message">
				<div>
					<span><span class="label bg-color-orange" ></span>公司名</span> 
				</div>
			</td>
			<td class="inbox-data-date hidden-xs">
				<div>
				</div>
			</td>
		</tr>
	<c:forEach  items="${groupvhic}"  var="user" >
		<tr id="msg1" class="unread">
			<td class="inbox-data-from hidden-xs hidden-sm">
				<div>
				${user.vehi_no}
				</div>
			</td>
			<td class="inbox-data-message">
				<div>
					<span><span class="label bg-color-orange" ></span>${user.comp_name}</span> 
				</div>
			</td>
			<td class="inbox-data-date hidden-xs">
				<div>
				</div>
			</td>
		</tr>
  
	</c:forEach>

</table>

