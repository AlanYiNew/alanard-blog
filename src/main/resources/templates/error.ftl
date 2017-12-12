
<#if error??>
<#switch error>
    <#case "0">
<#if Session.admin??>
{"error":0,"message":"success","uservalue":"${Session.admin.id}"}
<#else>
{"error":0,"message":"success","uservalue":""}
</#if>
	<#break>
	<#case "1">
{"error":1,"message":"<@spring.message "error_1"/>"}
	<#break>
	<#case "2">
{"error":2,"message":"<@spring.message "error_2"/>"}
	<#break>
	<#case "3">
{"error":3,"message":"<@spring.message "error_3"/>"}
	<#break>
	<#case "4">
{"error":4,"message":"<@spring.message "error_4"/>"}
	<#break>
	<#case "5">
{"error":5,"message":"<@spring.message "error_5"/>"}
	<#break>
	<#case "6">
{"error":6, "message":"<@spring.message "error_6"/>"}
	<#break>
	<#case "7">
{"error":7,"message":"<@spring.message "error_7"/>"}
	<#break>
	<#case "8">
{"error":8,"message":"<@spring.message "error_8"/>"}
	<#break>
	<#case "9">
{"error":9,"message":"<@spring.message "error_9"/>"}
	<#break>
	<#case "10">
{"error":10,"message":"<@spring.message "error_10"/>"}
	<#break>
	<#case "11">
{"error":11,"message":"<@spring.message "error_11"/>"}
	<#break>
	<#case "12">
{"error":12,"message":"<@spring.message "error_12"/>"}
	<#break>
	<#case "14">
{"error":14,"message":"<@spring.message "error_14"/>"}
	<#break>
	<#default>
{"error":13,"message":"<@spring.message "error_13"/>"}
	<#break>
</#switch>
<#else>	
	<#setting url_escaping_charset='utf-8'> 
	<!DOCTYPE html>  
	<html>
	<#include "head.ftl">
	<body>
	  <#if RequestParameters.err??>
	  	  <h1 class="errcode">403 :(</h1>
		  <h2 style="text-align:center"> <@spring.message "error_403"/></h2>
	  <#else>
		  <h1 class="errcode">404 :(</h1>
		  <h2 style="text-align:center"> <@spring.message "error_404"/></h2>
	  </#if>
	  <a class="home-btn" href='/'>Home</a>
	</body>
	</html>
	
</#if>
