/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.demo.security;

//import com.contract.entity.PrevilageTable;
import com.demo.jsf.util.URLUtils;
import com.demo.session.AbstractFacadeQuerySavvy;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;

/**
 *
 * @author Lucy
 */
@Named(value = "uRLBean")
@RequestScoped
public class URLBean {

    /**
     * Creates a new instance of URLBean
     */
    public URLBean() {
    }

    @EJB
    AbstractFacadeQuerySavvy ejbFacade;
    /**
     * Pass without the ERP name
     *
     * @param aLink
     * @return
     */
    public String encodedLink(String aLink) {
        if (aLink != null && !aLink.equalsIgnoreCase("")) {
            String encodedLink = URLUtils.encode(aLink);
            String redirectUrl = "/pos/savvypos?enc=" + encodedLink;
            return redirectUrl;
        }
        return null;
    }

    /**
     * Pass without the ERP name
     *
     * @param aLink
     */
    public void encryptedLink(String aLink) {
        if (aLink != null && !aLink.equalsIgnoreCase("")) {
            String redirectUrl = encodedLink(aLink);
//            FacesContext context = FacesContext.getCurrentInstance();
//            ELContext elContext = context.getELContext();
//            SalesOrderModule salesOrderModule = (SalesOrderModule) elContext.getELResolver().getValue(elContext, null, "salesOrderModule");
//
//            ReportChartsTypeInfoController reportChartsTypeInfoController = (ReportChartsTypeInfoController) elContext.getELResolver().getValue(elContext, null, "reportChartsTypeInfoController");
//            reportChartsTypeInfoController.setReportChartInfos(new ReportChartsInformation[20]);
//            reportChartsTypeInfoController.setReportChartTypes(new ReportChartsTypeInfo[20]);
//            salesOrderModule.redirectToReport();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
//            return str + "/inventory/xyz?encoded=" + encodedLink + "?faces-redirect=true";
        }
    }

//    public void redirectWithLable(String uri){
//        if (uri != null && !uri.equalsIgnoreCase("")) {
//            try{
//                ejbFacade.clearCatch();
//                List<PrevilageTable> previlageTableList = (List<PrevilageTable>) ejbFacade.findByStringId("PrevilageTable.findByLink1", "link", uri);
//              if(!previlageTableList.isEmpty()) {
//                  FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/"+previlageTableList.get(0).getLinkLable());
//              }
//            } catch (IOException e) {
//                e.printStackTrace(); // Handle the exception appropriately
//            }
//        }
//    }

}
