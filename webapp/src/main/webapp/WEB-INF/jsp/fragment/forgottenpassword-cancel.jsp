<%--
 ~ Password Management Servlets (PWM)
 ~ http://www.pwm-project.org
 ~
 ~ Copyright (c) 2006-2009 Novell, Inc.
 ~ Copyright (c) 2009-2021 The PWM Project
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
--%>
<%--
       THIS FILE IS NOT INTENDED FOR END USER MODIFICATION.
       See the README.TXT file in WEB-INF/jsp before making changes.
--%>


<%@ page import="password.pwm.http.servlet.PwmServletDefinition" %>
<%@ page import="password.pwm.http.servlet.forgottenpw.ForgottenPasswordServlet" %>
<%@ page import="password.pwm.http.tag.conditional.PwmIfTest" %>
<%@ page import="password.pwm.http.tag.value.PwmValue" %>
<%@ page language="java" session="true" isThreadSafe="true" contentType="text/html" %>
<%@ taglib uri="pwm" prefix="pwm" %>

<pwm:if test="<%=PwmIfTest.showCancel%>">
    <button type="button" name="button" class="btn" id="button-sendReset">
        <pwm:if test="<%=PwmIfTest.showIcons%>"><span class="btn-icon pwm-icon pwm-icon-times"></span></pwm:if>
        <pwm:display key="Button_Cancel"/>
    </button>
    <script type="module" nonce="<pwm:value name="<%=PwmValue.cspNonce%>"/>">
        import {PWM_MAIN} from "<pwm:url url="/public/resources/js/main.js" addContext="true"/>";
        PWM_MAIN.addEventHandler('button-sendReset', 'click',function() {
            PWM_MAIN.submitPostAction('<pwm:current-url/>', '<%=ForgottenPasswordServlet.ForgottenPasswordAction.reset%>');
        });
    </script>
</pwm:if>
