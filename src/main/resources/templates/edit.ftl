<#ftl output_format="XML">
<#setting url_escaping_charset='utf-8'> 
<!DOCTYPE html>  
<html>
<#include "head.ftl">
<body>
  <#include "header.ftl">
  <div class="content-wrapper">
        <div class="content-wrapper__inner">
            <div class="main-post-list">            	
				<form name="example" method="post" action="edit.do">				
					 <div class="input-group input-group-long">
					   <input type="text" name="title" value="${(post.title)!}" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "title"/></label>
					 </div>
				
					<div class="input-group input-group-short">
					   <input type="text" name="module" value="${(post.module)!}" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "module"/></label>
					</div>
					
					<div class="input-group input-group-short">
					   <input type="text" name="category" value="${(post.category)!}" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "category"/></label>
					</div>				
					
					<div>
						<div ><textarea id="editor_id" name="context"
									class="editor_style">
									${(post.context)!}
						</textarea>
						
						</div>
					</div>
					<div>
					<div class="post-submit-footer">
						
						<button type="submit" class="btn-submit"><@spring.message "submit" /></button>
					</div>
						
					
						<!--
						
						<img src="http://7xpvht.com1.z0.glb.clouddn.com/ring-alt.gif" id="wait" style="width:20px" class="loading"/>
						<i class="fa fa-2x fa-check green" id="correct" aria-hidden="true"></i>
						<i class="fa fa-2x fa-times red" id="fault" aria-hidden="true"></i>-->
					</div>
				</form>
		 
		</div>
		<#include "footer.ftl">
       </div>
  </div>
  <script src='/js/add-edit.js'></script>
  <script>
  	setupAjax('/user/${user.id}/userpage#blog');
  	setupSimeditor('/admin/${user.id}/upload.do');
  </script>
</body>
</html>