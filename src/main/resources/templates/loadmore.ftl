<#setting url_escaping_charset='utf-8'> 
<#list posts as post>   
    <li>
      <h2 class="post-list__post-title post-title">
        <a href="/user/${post.user.id}/post/${post.id}" title="link to post.title">${post.title}</a>
      </h2>
      <div class="post-content-wrapper">
      	${post.context}
      </div>
      <div class="post-list__meta">
      	<time datetime="${post.updateDate?date}" class="post-list__meta--date date">
      		${post.updateDate?date}
      	</time> • 
        <span class="post-list__meta--tags tags">
      		<a class="tag-link" href="/user/${post.user.id}/userpage?module=${post.module?url}#blog">${post.module}</a> >
		    <a class="tag-link tag-link-rightmargin" href="/user/${post.user.id}/userpage?category=${post.category?url}#blog">${post.category}</a>
	    </span>
	    <#include "admin-post-options.ftl">
	  </div>
      <hr class="post-list__divider">
    </li>
</#list>