<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/16 0016
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $(function(){
                    $("#subm").click(
                            function(){
                                $.post(
                                        "/param_return",
                                        JSON.stringify({supId:1001,supName:"name1001"}) ,
                                        function(json)
                                        {alert(json+"..."+json.supId);},
                                        "json"
                                );
                            }
                    );
                }
        );
        success:function(data){
            alert(data)
        }
        });
        // submit the form
        $('#ff').submit();
    </script>
</head>
<body>
<div id="p" class="easyui-panel" title="My Panel"
     style="width:500px;height:150px;padding:10px;background:#fafafa;"
     data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:true,maximizable:true">
   <div>
   </div>
   <div>

   </div>
   <div>
       <form id="ff" method="post">
           <div>
               <label for="name">姓名:</label>
               <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
           </div>
           <div>
               <label for="email">Email:</label>
               <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'" />
           </div>
           <div>
              请输入密码： <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"/>
           </div>
            <div>
           <input id="subm" name="submit" type="submit" class="easyui-submit" value="登陆"/>
           </div>
           <div>
           <input id="res" name="res" type="reset" class="easyui-res" value="重置"/>
           </div>


       </form>
   </div>
</div>
</body>
</html>
