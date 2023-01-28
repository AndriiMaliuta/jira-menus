package com.admal.menus;

import com.atlassian.fugue.Option;
import com.atlassian.jira.bc.user.UserPropertyService;
import com.atlassian.jira.entity.property.EntityPropertyOptions;
import com.atlassian.jira.entity.property.EntityPropertyService;
import com.atlassian.jira.entity.property.EntityPropertyType;
import com.atlassian.jira.event.user.property.UserPropertySetEvent;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.UserPropertyManager;
import com.atlassian.jira.util.SimpleErrorCollection;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.opensymphony.module.propertyset.PropertySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PropsMenu extends HttpServlet {
    private final TemplateRenderer templateRenderer;
    private final JiraAuthenticationContext jiraAuthenticationContext;
    private final UserPropertyManager userPropertyManager;
    private final UserPropertyService userPropertyService;

    public PropsMenu(TemplateRenderer templateRenderer, JiraAuthenticationContext jiraAuthenticationContext,
                     UserPropertyManager userPropertyManager,
                     UserPropertyService userPropertyService) {
        this.templateRenderer = templateRenderer;
        this.jiraAuthenticationContext = jiraAuthenticationContext;
        this.userPropertyManager = userPropertyManager;
        this.userPropertyService = userPropertyService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> context = new HashMap<>();
        ApplicationUser currentUser = jiraAuthenticationContext.getLoggedInUser();
        PropertySet propertySet = userPropertyManager.getPropertySet(currentUser);

        context.put("contextPath", req.getContextPath());
        context.put("propertySet", propertySet);

        UserPropertyService.EntityPropertyInput propertyInput = new UserPropertyService.EntityPropertyInput(
                LocalDateTime.now().toString(),
                currentUser.getKey(),
                currentUser.getId(),
                EntityPropertyType.USER_PROPERTY.getDbEntityName()
        );

        Option<UserPropertyService.EntityPropertyInput> noneInput = Option.none();
        UserPropertyService.PropertyInput entityPropertyInput2 =
                new UserPropertyService.PropertyInput("entityPropertyInput2", "entityPropertyInput2");

        userPropertyService.validateSetProperty(currentUser, currentUser.getId(), propertyInput);

        PropertySet currentUserPs = userPropertyManager.getPropertySet(currentUser);

//        userPropertyService.setProperty(
//                currentUser,
//                new EntityPropertyService.SetPropertyValidationResult(
//                        new SimpleErrorCollection(),
//                        Option.some(new EntityPropertyService.EntityPropertyInput(
//                                "AAAA",
//                                currentUser.getKey(),
//                                currentUser.getId(),
//                                EntityPropertyType.USER_PROPERTY.getDbEntityName())
//                        )
//                )
//        );

        context.put("currentUserPs", currentUserPs);


        templateRenderer.render("/templates/user-props-menu.vm", context, resp.getWriter());

    }
}
