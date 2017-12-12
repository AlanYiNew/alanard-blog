 <#if Session.admin?exists && Session.admin.id == user.id>
    <div class="admin-opt">
        <a class="btn-border-small" href="/user/${post.user.id}/post/${post.id}"> <@spring.message "continue_reading" /></a>
    	<a class="btn-border-small" href="/admin/${post.user.id}/post/${post.id}/edit"><@spring.message "edit" /></a>
  		<a class="btn-border-small btn-delete" href="/admin/${post.user.id}/post/${post.id}/delete.do"><@spring.message "delete" /></a>
	</div>
<#else>	
	<a class="btn-border-small" href="/user/${post.user.id}/post/${post.id}"> <@spring.message "continue_reading" /></a>
</#if>

