<#setting url_escaping_charset='utf-8'> 
<!DOCTYPE html>  
<html>
<#include "head.ftl">
<script src="/js/prism.js"></script>
<link rel="stylesheet" href="/css/prism.css">
<body>

  <#include "header.ftl">
  <div class="content-wrapper">
        <div class="content-wrapper__inner">
            <div class="main-post-list">
			    <article class="post-container post-container--single">
			
				  <header class="post-header">
				    <div class="post-meta">
				      <time datetime="${post.updateDate?date}" class="post-list__meta--date date">
				      	${post.updateDate?date}
				      </time> • 
				      <span class="post-meta__tags tags">于&nbsp;
				      	<a class="tag-link" href="/user/${post.user.id}/userpage?module=${post.module?url}#blog">${post.module}</a> >
				      	<a class="tag-link" href="/user/${post.user.id}/userpage?category=${post.category?url}#blog">${post.category}</a>
				      </span>
				      <#if Session.admin?exists && Session.admin.id == user.id>
	    				  <div class="admin-opt">
	    	                <a class="btn-border-small" href="/admin/${post.user.id}/post/${post.id}/edit"><@spring.message "edit" /></a>
	  		                <a class="btn-border-small btn-delete" href="/admin/${post.user.id}/post/${post.id}/delete.do"><@spring.message "delete" /></a>
		                  </div>
				   	  </#if>
				    </div>
				    <h1 class="post-title">${post.title}</h1>
				  </header>
				
				  <section class="post">
				   	${post.context}
				  </section>
				</article>	
			</div>
			<#include "footer.ftl">
        </div>
   </div>
</body>
</html>