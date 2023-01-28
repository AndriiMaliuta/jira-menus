package com.admal;

import com.atlassian.jira.bc.user.UserPropertyService;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.UserPropertyManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.atlassian.templaterenderer.TemplateRenderer;

import javax.inject.Inject;

@SuppressWarnings("ALL")
public class CsImports {
    @Inject
    public CsImports(@JiraImport UserPropertyManager userPropertyManager,
                     @ComponentImport UserPropertyService userPropertyService,
                     @ComponentImport TemplateRenderer templateRenderer,
                     @JiraImport JiraAuthenticationContext jiraAuthenticationContext) {

    }
}
