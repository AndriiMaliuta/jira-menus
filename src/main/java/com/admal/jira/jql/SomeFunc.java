package com.admal.jira.jql;

import com.atlassian.jira.user.ApplicationUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.JiraDataType;
import com.atlassian.jira.JiraDataTypes;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.jql.query.QueryCreationContext;
import com.atlassian.jira.plugin.jql.function.AbstractJqlFunction;
import com.atlassian.jira.util.MessageSet;
import com.atlassian.jira.util.NotNull;
import com.atlassian.query.clause.TerminalClause;
import com.atlassian.query.operand.FunctionOperand;
import com.google.common.collect.Iterables;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class SomeFunc extends AbstractJqlFunction {
    private static final Logger LOG = LoggerFactory.getLogger(SomeFunc.class);

    @Nonnull
    @Override
    public MessageSet validate(ApplicationUser applicationUser, @Nonnull FunctionOperand functionOperand, @Nonnull TerminalClause terminalClause) {
        return null;
    }

    public List<QueryLiteral> getValues(QueryCreationContext queryCreationContext, FunctionOperand operand, TerminalClause terminalClause) {
        return Collections.singletonList(new QueryLiteral(operand, Iterables.get(operand.getArgs(), 0)));
    }

    public int getMinimumNumberOfExpectedArguments() {
        return 1;
    }

    public JiraDataType getDataType() {
        return JiraDataTypes.TEXT;
    }
}