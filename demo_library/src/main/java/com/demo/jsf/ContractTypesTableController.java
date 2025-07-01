package com.demo.jsf;

import com.demo.entity.ContractTypesTable;
import com.demo.jsf.util.JsfUtil;
import com.demo.jsf.util.JsfUtil.PersistAction;
import com.demo.session.ContractTypesTableFacade;

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

@Named("contractTypesTableController")
@SessionScoped
public class ContractTypesTableController implements Serializable {

    @EJB
    private com.demo.session.ContractTypesTableFacade ejbFacade;
    @EJB
    private com.demo.session.AbstractFacadeQuerySavvy ejbFacade1;
    private List<ContractTypesTable> items = null;
    private List<ContractTypesTable> multiselectionItems = null;
    private List<ContractTypesTable> createItems = null;
    private List<ContractTypesTable> editItems = null;
    private List<ContractTypesTable> filteredValues = null;
    private ContractTypesTable selected;
    private ContractTypesTable selected1;
    private ContractTypesTable selected2 = new ContractTypesTable();
    private String dataName = "ContractTypesTable";
    protected int first;

    public ContractTypesTableController() {
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

    public ContractTypesTable getSelected() {
        return selected;
    }

    public void setSelected(ContractTypesTable selected) {
        this.selected = selected;
    }

    public ContractTypesTable getSelected1() {
        return selected1;
    }

    public void setSelected1(ContractTypesTable selected1) {
        this.selected1 = selected1;
    }

    public ContractTypesTable getSelected2() {
        return selected2;
    }

    public void setSelected2(ContractTypesTable selected2) {
        this.selected2 = selected2;
    }

    public void cancelUpdate() {
        selected1 = null;

        editItems = null;
    }

    public void discard() {
        selected = null;
        for (ContractTypesTable item : getCreateItems()) {
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

    private ContractTypesTableFacade getFacade() {
        return ejbFacade;
    }

    public ContractTypesTable prepareCreate() {
        createItems = new ArrayList<>();

        int tempid = 1;

        selected = new ContractTypesTable();
        selected.setTempId(tempid);

        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public ContractTypesTable prepareCopy() {

        if (createItems == null) {
            createItems = new ArrayList<>();
        }
        selected = multiselectionItems.get(0);
        selected.setId(null);
        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public ContractTypesTable prepareCreateInCreate() {

        selected1 = new ContractTypesTable();
        createItems = preparingTempId(selected1, createItems);
        initializeEmbeddableKey();
        return selected1;
    }

    public ContractTypesTable prepareCreate1() {
        selected = new ContractTypesTable();
        int tempId = 0;
        for (ContractTypesTable p : createItems) {
            if (p.getTempId() > tempId) {
                tempId = p.getTempId();
            }
        }
        selected.setTempId(tempId + 1);
        createItems.add(selected);
        return selected;
    }

    public ContractTypesTable prepareCreateInEdit() {

        selected1 = new ContractTypesTable();
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
        createItems.add(new ContractTypesTable());
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

        for (ContractTypesTable item : getCreateItems()) {
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
        for (ContractTypesTable item : getEditItems()) {
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

        for (ContractTypesTable item : getEditItems()) {
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

    public void removeInCreate(ContractTypesTable item) {

        if (item.getId() == null) {
            createItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            createItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeInEdit(ContractTypesTable item) {

        if (item.getId() == null) {
            editItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            editItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeRecord(ContractTypesTable item) {

        if (item.getId() != null) {
            getFacade().remove(item);
        }

    }

    public void removeList(List<ContractTypesTable> aList) {

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

    public List<ContractTypesTable> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<ContractTypesTable> items) {
        this.items = items;
    }

    public List<ContractTypesTable> getMultiselectionItems() {
        return multiselectionItems;
    }

    public void setMultiselectionItems(List<ContractTypesTable> multiselectionItems) {
        this.multiselectionItems = multiselectionItems;
    }

    public List<ContractTypesTable> getFilteredValues() {
        return filteredValues;
    }

    public void setFilteredValues(List<ContractTypesTable> filteredValues) {
        this.filteredValues = filteredValues;
    }

    public List<ContractTypesTable> getCreateItems() {
        if (createItems == null) {
            createItems = getFacade().findAll();
        }
        return createItems;
    }

    public void setCreateItems(List<ContractTypesTable> createItems) {
        this.createItems = createItems;
    }

    public List<ContractTypesTable> getEditItems() {
        if (editItems == null) {
            editItems = getFacade().findAll();
        }
        return editItems;
    }

    public void setEditItems(List<ContractTypesTable> editItems) {
        this.editItems = editItems;
    }

    private List<ContractTypesTable> preparingTempId(ContractTypesTable item, List<ContractTypesTable> aList) {
        int tempId = 0;
        if (!aList.isEmpty()) {
            for (ContractTypesTable itm : aList) {
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
            for (ContractTypesTable item : multiselectionItems) {
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

    public ContractTypesTable getContractTypesTable(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ContractTypesTable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ContractTypesTable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ContractTypesTable.class)
    public static class ContractTypesTableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContractTypesTableController controller = (ContractTypesTableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contractTypesTableController");
            return controller.getContractTypesTable(getKey(value));
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
            if (object instanceof ContractTypesTable) {
                ContractTypesTable o = (ContractTypesTable) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ContractTypesTable.class.getName()});
                return null;
            }
        }

    }

}
