<head>

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<#if post??>
<title>${post.title}</title>
<#elseif user??>
<title>${user.username}'s blog</title>
<#elseif  RequestParameters.err??>
<title>403</title>
<#else>
<title>404</title>
</#if>
<meta name="renderer" content="webkit">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<meta name="theme-color" content="#333333">
<link rel="icon" type="image/png" href="/images/favicon-32x32.png" sizes="32x32" />
<link rel="icon" type="image/png" href="/images/favicon-16x16.png" sizes="16x16" />

<link rel="stylesheet" href="/css/vno.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/message.css">
<link rel="stylesheet" type="text/css" href="/js/simditor-2.3.6/styles/simditor.css" />
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/main.js"></script>
<script type="text/javascript" src="/js/simditor-2.3.6/scripts/jquery.min.js"></script>
<script type="text/javascript" src="/js/simditor-2.3.6/scripts/module.js"></script>
<script type="text/javascript" src="/js/simditor-2.3.6/scripts/hotkeys.js"></script>
<script type="text/javascript" src="/js/simditor-2.3.6/scripts/uploader.js"></script>
<script type="text/javascript" src="/js/simditor-2.3.6/scripts/simditor.js"></script>
<script src='/js/jquery.md5.js'></script>
<script src='/js/message.js'></script>
<!--[if IE]>
  <script src='/js/messagIE.js'></script>
<![endif]-->

</head>