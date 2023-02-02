package com.admal.ui;

import com.atlassian.fugue.Option;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.bc.user.UserPropertyService;
import com.atlassian.jira.entity.property.EntityPropertyType;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.jql.builder.JqlQueryBuilder;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.UserPropertyManager;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.query.Query;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.opensymphony.module.propertyset.PropertySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class PropsMenu extends HttpServlet {
    private final TemplateRenderer templateRenderer;
    private final JiraAuthenticationContext jiraAuthenticationContext;
    private final UserPropertyManager userPropertyManager;
    private final UserPropertyService userPropertyService;
    private final SearchService searchService;

    public PropsMenu(TemplateRenderer templateRenderer, JiraAuthenticationContext jiraAuthenticationContext,
                     UserPropertyManager userPropertyManager,
                     UserPropertyService userPropertyService, SearchService searchService) {
        this.templateRenderer = templateRenderer;
        this.jiraAuthenticationContext = jiraAuthenticationContext;
        this.userPropertyManager = userPropertyManager;
        this.userPropertyService = userPropertyService;
        this.searchService = searchService;
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

        // get properties
        PropertySet currentUserPs = userPropertyManager.getPropertySet(currentUser);
        currentUserPs.setAsActualType("", "");


        context.put("currentUserPs", currentUserPs);

        templateRenderer.render("/templates/user-props-menu.vm", context, resp.getWriter());

    }

    private void getUserProps(ApplicationUser user) throws SearchException {
        Query query = JqlQueryBuilder.newBuilder()
                .buildQuery();
        SearchResults results = searchService.search(user, query, PagerFilter.getUnlimitedFilter());
        List<Issue> issues = results.getIssues();


    }
}
