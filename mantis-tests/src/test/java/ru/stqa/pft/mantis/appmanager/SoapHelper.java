package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.IssueStatus;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"));
        return Arrays.asList(projects).stream()
                .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soap.connectionURL")));
        return mc;
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        int issueId = mc.mc_issue_add(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"), issueData).intValue();

        return getIssueById(issueId);
    }

    public Issue getIssueById(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"), BigInteger.valueOf(id));

        return new Issue().withId(issueData.getId().intValue())
                .withSummary(issueData.getSummary())
                .withDescription(issueData.getDescription())
                .withStatus(new IssueStatus().withId(issueData.getStatus().getId().intValue())
                        .withName(issueData.getStatus().getName()))
                .withProject(new Project().withId(issueData.getProject().getId().intValue())
                        .withName(issueData.getProject().getName()));
    }
}
