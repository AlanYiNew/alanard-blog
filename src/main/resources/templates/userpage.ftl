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
			  <ol class="post-list">		  
			  <#list posts as post>   
			    <li>
			      <h2 class="post-list__post-title post-title">
			        <a href="/user/${user.id}/post/${post.id}" title="link to post.title">${post.title}</a>
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
			  </ol>
    

			  <nav class="pagination"  role="navigation">
			  	  <div class="sk-cube-grid" style="width:25px;height:25px;display:none">
					  <div class="sk-cube sk-cube1" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube2" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube3" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube4" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube5" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube6" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube7" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube8" style="background-color:#ccc"></div>
					  <div class="sk-cube sk-cube9" style="background-color:#ccc"></div>
				  </div>
			      <a class="older-posts btn btn-small btn-tertiary" href="/user/${user.id}/loadmore">
			          <@spring.message "loadmore" />
			      </a>
			  </nav>

			</div>
		<#include "footer.ftl">
    	</div>
	</div>
	<script>
	
	if (window.location.href.indexOf("#blog") == -1 && $('.btn-mobile-menu').css('display') == 'block'){
		$('.panel-cover').height($('.panel-cover').height()*1.1);
	}
  	
	var pageNum = 1;
	$("a.older-posts").on('click',function(e){
		e.preventDefault();
		param = {
				"currentPage":++pageNum,
		}
		
		<#if RequestParameters.module??>
			param['module'] = '${RequestParameters.module}';
		</#if>
		
		<#if RequestParameters.category??>
			param['category'] = '${RequestParameters.category}';
		</#if>
			
		
		$.ajax({
			url:"/user/${user.id}/loadmore.do",
			type:"POST",
			dataType:"text",
			data:param,
			cache:false,
			beforeSend:function(){
				$('a.older-posts').hide();
				$('.sk-cube-grid').show();
				
			},
			
			success:function(data){
				if (data != ''){
					var $newpost = $(data);
					$newpost.hide().appendTo(".post-list")
					Prism.highlightAll();
					$newpost.slideDown("slow");
				}
				$('.sk-cube-grid').hide();
			    $('a.older-posts').show();
			},
			error:function(data){
				$('.sk-cube-grid').hide();
				$('a.older-posts').show();
				console.log("ajax error");
			}
			});			

	});
	
	
	function messageHandler(result){
		if (result.error != 0){
   			$.message({
		        message:result.message,
		        type:'error'
		    });
   		}
	}
	$('a.btn-delete').on('click',function(e){
		e.preventDefault();
		if(!window.confirm('<@spring.message "confirm_delete"/>')){
			return;
		}
		
		var url = $(this).attr('href');
		$.ajax({
			type: "POST",
            url: url,
            
            success: function(data)
            {
           		var result = JSON.parse(data);
           		messageHandler(result);
           		if (result.error == 0){
           			location.reload();
           		}               
            },
            error: function(data){
           		$.message({
			        message:'server error',
			        type:'error'
			    });
            }
		});
	})
	 

</script>
</body>
</html>