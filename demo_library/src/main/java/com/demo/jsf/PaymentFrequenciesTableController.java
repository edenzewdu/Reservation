package com.demo.jsf;

import com.demo.entity.PaymentFrequenciesTable;
import com.demo.jsf.util.JsfUtil;
import com.demo.jsf.util.JsfUtil.PersistAction;
import com.demo.session.PaymentFrequenciesTableFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import java.util.ArrayList;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@Named("paymentFrequenciesTableController")
@SessionScoped
public class PaymentFrequenciesTableController implements Serializable {

    @EJB
    private com.demo.session.PaymentFrequenciesTableFacade ejbFacade;
    @EJB
    private com.demo.session.AbstractFacadeQuerySavvy ejbFacade1;
    private List<PaymentFrequenciesTable> items = null;
    private List<PaymentFrequenciesTable> multiselectionItems = null;
    private List<PaymentFrequenciesTable> createItems = null;
    private List<PaymentFrequenciesTable> editItems = null;
    private List<PaymentFrequenciesTable> filteredValues = null;
    private PaymentFrequenciesTable selected;
    private PaymentFrequenciesTable selected1;
    private PaymentFrequenciesTable selected2 = new PaymentFrequenciesTable();
    private String dataName = "PaymentFrequenciesTable";
    protected int first;

    public PaymentFrequenciesTableController() {
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public String getDataName() {
        return this.dataName;
    }

    public void setDataName(final String dataName) {
        this.dataName = dataName;
    }

    public PaymentFrequenciesTable getSelected() {
        return selected;
    }

    public void setSelected(PaymentFrequenciesTable selected) {
        this.selected = selected;
    }

    public PaymentFrequenciesTable getSelected1() {
        return selected1;
    }

    public void setSelected1(PaymentFrequenciesTable selected1) {
        this.selected1 = selected1;
    }

    public PaymentFrequenciesTable getSelected2() {
        return selected2;
    }

    public void setSelected2(PaymentFrequenciesTable selected2) {
        this.selected2 = selected2;
    }

    public void cancelUpdate() {
        selected1 = null;

        editItems = null;
    }

    public void discard() {
        selected = null;
        for (PaymentFrequenciesTable item : getCreateItems()) {
            if (item.getId() != null) {
                getFacade().remove(item);
            }

        }
        createItems = null;
        items = null;
        if (!JsfUtil.isValidationFailed()) {
            // Invalidate list of items to trigger re-query.
            JsfUtil.addSuccessMessage("All records are removed");
        }
    }

    public void cancelCreate() {
        selected = null;
        createItems = null;
        items = null;
    }

    public void refreshList() {
        items = null;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaymentFrequenciesTableFacade getFacade() {
        return ejbFacade;
    }

    public PaymentFrequenciesTable prepareCreate() {
        createItems = new ArrayList<>();

        int tempid = 1;

        selected = new PaymentFrequenciesTable();
        selected.setTempId(tempid);

        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public PaymentFrequenciesTable prepareCopy() {

        if (createItems == null) {
            createItems = new ArrayList<>();
        }
        selected = multiselectionItems.get(0);
        selected.setId(null);
        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public PaymentFrequenciesTable prepareCreateInCreate() {

        selected1 = new PaymentFrequenciesTable();
        createItems = preparingTempId(selected1, createItems);
        initializeEmbeddableKey();
        return selected1;
    }

    public PaymentFrequenciesTable prepareCreate1() {
        selected = new PaymentFrequenciesTable();
        int tempId = 0;
        for (PaymentFrequenciesTable p : createItems) {
            if (p.getTempId() > tempId) {
                tempId = p.getTempId();
            }
        }
        selected.setTempId(tempId + 1);
        createItems.add(selected);
        return selected;
    }

    public PaymentFrequenciesTable prepareCreateInEdit() {

        selected1 = new PaymentFrequenciesTable();
        editItems = preparingTempId(selected1, editItems);
        initializeEmbeddableKey();
        return selected1;
    }

    public void prepareEdit() {
        editItems = new ArrayList<>();
        selected = multiselectionItems.get(0);
        editItems.add(selected);
    }

    public String saveAndClose(String linkName) {
        cancelUpdate();
        cancelCreate();
        linkName += ".xhtml?faces-redirect=true";
        return linkName;
    }

    public String saveAndAddNew(String linkName) {
        createItems = new ArrayList<>();
        createItems.add(new PaymentFrequenciesTable());
        linkName += ".xhtml?faces-redirect=true";
        return linkName;
    }

    public String saveAndAddContinue(String linkName) {
        createItems = new ArrayList<>();
        createItems.add(selected);
        linkName += ".xhtml?faces-redirect=true";
        return linkName;
    }

    public void save() {

        for (PaymentFrequenciesTable item : getCreateItems()) {
            if (item.getId() == null) {
                getFacade().create(item);
            } else {
                getFacade().edit(item);
            }
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            JsfUtil.addSuccessMessage("Saved");
        }
    }

    public void saveRow() {
        for (PaymentFrequenciesTable item : getEditItems()) {
            if (item.getId() == null) {
                getFacade().create(item);
            } else {
                getFacade().edit(item);
            }

        }
        if (!JsfUtil.isValidationFailed()) {
            // Invalidate list of items to trigger re-query.
            JsfUtil.addSuccessMessage("Saved");
        }
    }

    public void saveInEdit() {

        for (PaymentFrequenciesTable item : getEditItems()) {
            if (item.getId() == null) {
                getFacade().create(item);
            } else {
                getFacade().edit(item);
            }
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            JsfUtil.addSuccessMessage("Saved");
        }
    }

    public void createInEdit() {
        getFacade().create(selected1);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE);
    }

    public void removeInCreate(PaymentFrequenciesTable item) {

        if (item.getId() == null) {
            createItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            createItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeInEdit(PaymentFrequenciesTable item) {

        if (item.getId() == null) {
            editItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            editItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeRecord(PaymentFrequenciesTable item) {

        if (item.getId() != null) {
            getFacade().remove(item);
        }

    }

    public void removeList(List<PaymentFrequenciesTable> aList) {

        if (aList != null && !aList.isEmpty()) {
            getFacade().removeCollection(aList);
        }

    }

    public void destroy() {
        persist(PersistAction.DELETE);
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            multiselectionItems = null;
        }
    }

    public List<PaymentFrequenciesTable> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<PaymentFrequenciesTable> items) {
        this.items = items;
    }

    public List<PaymentFrequenciesTable> getMultiselectionItems() {
        return multiselectionItems;
    }

    public void setMultiselectionItems(List<PaymentFrequenciesTable> multiselectionItems) {
        this.multiselectionItems = multiselectionItems;
    }

    public List<PaymentFrequenciesTable> getFilteredValues() {
        return filteredValues;
    }

    public void setFilteredValues(List<PaymentFrequenciesTable> filteredValues) {
        this.filteredValues = filteredValues;
    }

    public List<PaymentFrequenciesTable> getCreateItems() {
        if (createItems == null) {
            createItems = getFacade().findAll();
        }
        return createItems;
    }

    public void setCreateItems(List<PaymentFrequenciesTable> createItems) {
        this.createItems = createItems;
    }

    public List<PaymentFrequenciesTable> getEditItems() {
        if (editItems == null) {
            editItems = getFacade().findAll();
        }
        return editItems;
    }

    public void setEditItems(List<PaymentFrequenciesTable> editItems) {
        this.editItems = editItems;
    }

    private List<PaymentFrequenciesTable> preparingTempId(PaymentFrequenciesTable item, List<PaymentFrequenciesTable> aList) {
        int tempId = 0;
        if (!aList.isEmpty()) {
            for (PaymentFrequenciesTable itm : aList) {
                if (itm.getTempId() != null && itm.getTempId() > tempId) {
                    tempId = itm.getTempId();
                }
            }
        }
        tempId += 1;
        item.setTempId(tempId);
        aList.add(item);
        return aList;
    }

    private void persist(PersistAction persistAction) {
        if (!multiselectionItems.isEmpty()) {
            for (PaymentFrequenciesTable item : multiselectionItems) {
                setEmbeddableKeys();
                try {
                    if (persistAction != PersistAction.DELETE) {
                        getFacade().edit(item);
                    } else {
                        getFacade().remove(item);
                    }

                } catch (EJBException ex) {
                    String msg = "";
                    Throwable cause = ex.getCause();
                    if (cause != null) {
                        msg = cause.getLocalizedMessage();
                    }
                    if (msg.length() > 0) {
                        JsfUtil.addErrorMessage(msg);
                    } else {
                        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            }
        }
    }

    public PaymentFrequenciesTable getPaymentFrequenciesTable(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PaymentFrequenciesTable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PaymentFrequenciesTable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PaymentFrequenciesTable.class)
    public static class PaymentFrequenciesTableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaymentFrequenciesTableController controller = (PaymentFrequenciesTableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paymentFrequenciesTableController");
            return controller.getPaymentFrequenciesTable(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PaymentFrequenciesTable) {
                PaymentFrequenciesTable o = (PaymentFrequenciesTable) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PaymentFrequenciesTable.class.getName()});
                return null;
            }
        }

    }

}
