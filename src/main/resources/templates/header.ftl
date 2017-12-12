<span class="mobile btn-mobile-menu">
    <i class="fa fa-list btn-mobile-menu__icon"></i>
    <i class="fa fa-angle-up btn-mobile-close__icon hidden"></i>
</span>
<#if posts??>
<header class="panel-cover" style="background-image: url(&quot;${user.background}&quot;); ">
<#else>
<header class="panel-cover panel-cover--collapsed" style="background-image: url(&quot;${user.background}&quot;);">
</#if>
    <div class="panel-main">
    <div class="panel-main__inner panel-inverted">
    <div class="panel-main__content">

        <a href="/user/${user.id}/userpage" title="${user.username}'s blog"><img src="${user.pofolioPic}" width="80" alt="${user.username}'s blog logo" class="panel-cover__logo logo"></a>
        <h1 class="panel-cover__title panel-title"><a href="/user/${user.id}/userpage" title="link to homepage for ${user.username}'s blog">${user.username}'s blog</a></h1>
        
        <hr class="panel-cover__divider">
        <p class="panel-cover__description">${user.introduction}</p>
        <hr class="panel-cover__divider panel-cover__divider--secondary">

        <div class="navigation-wrapper ">
          <div>
          <nav class="cover-navigation cover-navigation--primary">
            <ul class="navigation">
              <li class="navigation__item">
              	<#if index??>
              		<a href="#blog" title="><@spring.message "blog" />" class="blog-button"><@spring.message "blog" /></a>
              	<#else>
              		<a href="/user/${user.id}/userpage#blog" title="><@spring.message "blog" />" class="blog-button"><@spring.message "blog" /></a>
              	</#if>
              </li>
              <li class="navigation__item"><a href="/user/${user.id}/userpage?module=About me#blog">About</a></li>
              <li class="navigation__item"><a href="/user/${user.id}/tags">Tags</a></li>
              <#if Session.admin??>
              	 <#if  Session.admin.id == user.id>
                 	<li class="navigation__item"><a href="/admin/${admin.id}/post/add"><@spring.message "create"/></a></li>
                 	<li class="navigation__item"><a href="/admin/${admin.id}/changeinfo"><@spring.message "change_Info"/></a></li>
                 <#else>	
                 	<li class="navigation__item"><a href="/user/${admin.id}/userpage"><@spring.message "my_blog"/></a></li>
              	 </#if>
              	
              	 
              	 <li class="navigation__item"><a href="/admin/signout.do"><@spring.message "sign_Out"/></a></li>
              <#else>
              	 <li class="navigation__item"><a href="/admin/login"><@spring.message "sign_In"/></a></li>
              </#if>
            </ul>
          </nav>
          </div>
          <div>
          <nav class="cover-navigation navigation--social">
  <ul class="navigation">

  <!-- Weibo-->
  

  <!-- Github -->
  <#if user.github?has_content>
	  <li class="navigation__item">
	    <a href="${user.github}" title="<@spring.message "to_github" />" target="_blank">
	      <i class="social fa fa-github"></i>
	      <span class="label">Github</span>
	    </a>
	  </li>
  </#if>


<!-- Stack Overflow -->
        

  <!-- Google Plus -->
  

<!-- Facebook -->

  
<!-- Twitter -->

  

  



  </ul>
</nav>

          </div>
        </div>

      </div>

    </div>

    <div class="panel-cover--overlay cover-purple"></div>
  </div>
  
  <div class="dialog" style="display:none">
    <div class="content">
	    <span class="left-half">Alan</span><span class="right-half">ard</span>
		<div class="switch">
		
	         <input type="email" placeholder="<@spring.message "email"/>"></input>
	         <div class="underline"></div>
	         
	       
	    	 <input type="password" placeholder="<@spring.message "password"/>"></input>
	    	 <div class="underline"></div>	    	 
		
		</div>
    
	    <footer>
    		 <a class="sign-up" href="admin/signup">
        	 	<@spring.message "sign_Up"/>
        	 </a><span style="display:inline-block;margin-left:4px">|</span>
    	     <a class="forget-password" href="admin/resetPassword">
        	 	<@spring.message "forget_Password"/>
        	 </a>
        	 <button type="button" data-modal-btn="dismiss" class="submit">></button>
	    </footer>
	</div>
  </div>
</header>