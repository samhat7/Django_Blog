/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import bean.CustomerCallHistory;
import bean.CustomerContact;
import bean.admin.EmailTemplate;
import common2.Common2View;
import component.JComboBoxPallete;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import service.util.AbstractIBean;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class CustomerContact_RULE extends BusinessRuleWrapper {

    @Override
    public boolean beforeSave(AbstractIBean bean) {
        CustomerContact contact = (CustomerContact) bean;
        if (contact.firstName == null || contact.firstName.isEmpty()) {
            contact.firstName = "XXX";
        }
        if (contact.lastName == null || contact.lastName.isEmpty()) {
            contact.lastName = "XXX";
        }
        return true;
    }

    @Override
    public void runFocusLost(JComponent comp) {
        if (comp.getName().equals("customerClass")) {
            setupPriceLevel(getValue("customerClass"));
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnRemoveFromList")) {
            removeFromList();
        } else if (comp.getName().equals("btnSendProposal")) {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    sendProposal();
                }
            });
        } else if (comp.getName().equals("btnPresentation")) {
            presentation();
        } else if (comp.getName().equals("btnFollowUp")) {
            followUp();
        } else if (comp.getName().equals("btnSales")) {
            sales();
        } else if (comp.getName().equals("btnNegative")) {
            negative();
        } else if (comp.getName().equals("btnInitialCall")) {
            initialCall();
        } else if (comp.getName().equals("btnNewOpportunity")) {
            newOpportunity();
        } else if (comp.getName().equals("btnSendToAll")) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    sendToAll();
                }
            });
        } else if (comp.getName().equals("btnSendToSelected")) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    sendToSelected();
                }
            });
        } else if (comp.getName().equals("btnSendToFax")) {
            printToFax();
        }
    }

    private void followUp() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        contact.customerType = "FOLLOW UP";
//        if (contact.nextCallDate == null) {
//            contact.nextCallDate = new Date();
//        }
        contact.save();
        createCallHistory(contact);
        redisplayRecord();
        PanelUtil.showMessageToScreen("Customer for follow up.");
    }

    private void initialCall() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        contact.customerType = "INITIAL";
//        if (contact.nextCallDate == null) {
//            contact.nextCallDate = new Date();
//        }
        contact.save();
        createCallHistory(contact);
        redisplayRecord();
        PanelUtil.showMessageToScreen("Customer for initial call.");
    }

    private void negative() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        contact.customerType = "NEGATIVE";
        contact.save();
        createCallHistory(contact);
        redisplayRecord();
        PanelUtil.showMessageToScreen("Customer is negative.");
    }

    private void newOpportunity() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        contact.customerType = "NEW OPPORTUNITY";
//        if (contact.nextCallDate == null) {
//            contact.nextCallDate = new Date();
//        }
        contact.save();
        createCallHistory(contact);
        redisplayRecord();
        PanelUtil.showMessageToScreen("Customer for new opportunity.");
    }

    private void presentation() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        contact.customerType = "PRESENTATION";
//        if (contact.nextCallDate == null) {
//            contact.nextCallDate = new Date();
//        }
        contact.save();
        createCallHistory(contact);
        redisplayRecord();
        PanelUtil.showMessageToScreen("Customer for presentation.");
    }

    private void printToFax() {
        CustomerContact contact = (CustomerContact) getBean();
        try {
            JFileChooser f = new JFileChooser("D:/Marketing/proposal/school/School Fax Proposal.doc");
            f.showOpenDialog(Common2View.mainView.getFrame());
            File file = f.getSelectedFile();
            Desktop.getDesktop().print(file);
        } catch (IOException ex) {
            Logger.getLogger(CustomerContact_RULE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sales() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        contact.customerType = "SALES ACCOUNT";
//        if (contact.nextCallDate == null) {
//            contact.nextCallDate = new Date();
//        }
        contact.save();
        createCallHistory(contact);
        redisplayRecord();
        PanelUtil.showMessageToScreen("Customer for sales account.");
    }

    private void sendProposal() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        if (!contact.isSendProposal()) {
            contact.sendProposal = true;
            contact.save();
        }
        PanelUtil.showMessageToScreen("Proposal is now requested");
    }

    private void sendToSelected(CustomerContact contact) {
        try {
            EmailTemplate template = getTemplate(contact);
            if (template == null) {
                return;
            }
            bean.admin.SentEmail email = new bean.admin.SentEmail();
            email.customerId = contact.personId;
            email.sentDate = new Date();
            email.recipient = contact.email;
            email.content = template.content;
            email.subject = template.subject;
            email.save();

            test.SimpleMail.sendEmail(template.subject, template.content, contact.email, template.attachment);
            test.SimpleMail.sendEmail(template.subject, template.content, contact.email, template.attachment);
            contact.sendProposal = false;
            contact.save();
            refreshRecords();
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    private void sendToSelected() {
        CustomerContact contact = (CustomerContact) getBean();
        sendToSelected(contact);
    }

    private void sendToAll() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                sendToAllNow();
            }
        });
        t.start();
    }

    private void sendToAllNow() {
        List<CustomerContact> lst = DBClient.getList("SELECT a FROM CustomerContact a WHERE a.sendProposal=true");
        for (CustomerContact contact : lst) {
            sendToSelected(contact);
        }
        refreshRecords();
        PanelUtil.showMessageToScreen("Proposal sent to the list.");
    }
    List<bean.admin.EmailTemplate> templateLst;
    bean.admin.EmailTemplate sampleTemplate;

    private bean.admin.EmailTemplate getTemplate(CustomerContact contact) {
        if (templateLst == null) {
            templateLst = DBClient.getList("SELECT a FROM EmailTemplate a ORDER BY a.templateName");
        }
        if (templateLst != null) {
            for (EmailTemplate template : templateLst) {
                if (contact.customerClass.startsWith(template.templateName)) {
                    return template;
                }
                if ("SAMPLE".equals(template.templateName)) {
                    sampleTemplate = template;
                }
            }
            //create a template from SAMPLE
            if (sampleTemplate == null) {
                sampleTemplate = new EmailTemplate();
                sampleTemplate.templateName = "SAMPLE";
                sampleTemplate.subject = "Proposal for ${customerClass}";
                sampleTemplate.content = "Proposal for ${customerClass}";
                sampleTemplate.save();

                sampleTemplate.templateName = contact.customerClass;
                sampleTemplate.save();
                templateLst = null;
                return sampleTemplate;
            }
        }
        PanelUtil.showErrorMessageToScreen("No email template defined for " + contact.customerClass + ".");
        return null;
    }

    private void removeFromList() {
        CustomerContact contact = (CustomerContact) getBean();
        contact.salesPersonId1 = contact.extractLoginPersonId();
        contact.sendProposal = false;
        contact.save();
        refreshRecords();
        PanelUtil.showMessageToScreen("Removed record.");
    }

    protected void displayThisWeek(String type) {
        String dateToday = util.DateUtil.formatDateToSql(new Date());
        String datePlus7 = util.DateUtil.formatDateToSql(util.DateUtil.addDay(new Date(), 7));
        List lst = DBClient.getList("SELECT a FROM CustomerContact a WHERE a.customerType='", type, "' AND a.nextCallDate BETWEEN '", dateToday, "' AND '" + datePlus7 + "'");
        setList(lst);
    }

    protected void displayToday(String type) {
        String dateToday = util.DateUtil.formatDateToSql(new Date());
        List lst = DBClient.getList("SELECT a FROM CustomerContact a WHERE a.customerType='", type, "' AND a.nextCallDate='", dateToday, "'");
        setList(lst);
    }

    public static void createCallHistory(CustomerContact call) {
        CustomerCallHistory history = new CustomerCallHistory();
        history.setCustomerCallId(1);
        history.setCallDate(call.nextCallDate);
        history.setNotesBytes(call.note);
        history.setCallType(call.customerType);
        history.setContactPerson(call.getContactPerson());
        history.setCustomerId(call.personId);
        history.setPhoneNumber(call.contactNumber);
        history.setSubject(call.customerType);
        history.setCallResult(call.note);
        history.save();
    }

    private void setupPriceLevel(String cls) {
        JComboBoxPallete cbo = (JComboBoxPallete) getComponent("priceLevel");
        if (cbo == null) {
            return;
        }
        List lst = cbo.getList();
        lst.clear();
        if ("SCHOOL".equals(cls)) {
            lst.add("PE");
            lst.add("PES");
            lst.add("PESC");
        } else if ("WATER DISTRICT".equals(cls)) {
            lst.add("SMALL");
            lst.add("AVERAGE");
            lst.add("MEDIUM");
            lst.add("BIG");
            lst.add("LARGE");
            lst.add("VERY LARGE");
        } else {  //for LGU, HOSPITAL, OTHERS

            lst.add("SMALL");
            lst.add("MEDIUM");
            lst.add("LARGE");
        }
        cbo.updateUI();
    }
}
