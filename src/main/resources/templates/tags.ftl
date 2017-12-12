<#setting url_escaping_charset='utf-8'> 
<!DOCTYPE html>  
<html>
<#include "head.ftl">
<body>
  <#include "header.ftl">
  <div class="content-wrapper">
        <div class="content-wrapper__inner">
            <article class="post-container post-container--single">

  <header class="post-header">
    <h1 class="post-title">标签</h1>
  </header>

  <section class="post">
  	<#list result?keys as it> 
	<h3 id="${it}"><a class="header-anchor" href="#${it}">¶</a> <a class="module-style" href="/user/${user.id}/userpage?module=${it?url}#blog">${it}</a></h3>
	<#assign items = result[it]>
	<ul>
		<#list items as item> 
			<li><a href="/user/${user.id}/userpage?category=${item?url}#blog">${item}</a></li>
		</#list>
	</ul>
	</#list>
  </section>
</article>          	
					 
		</div>
		<#include "footer.ftl">
       </div>
  </div>
  
</body>
</html>