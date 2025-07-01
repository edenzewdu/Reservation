package com.demo.jsf;

import com.demo.entity.PartyRoleTypesTable;
import com.demo.jsf.util.JsfUtil;
import com.demo.jsf.util.JsfUtil.PersistAction;
import com.demo.session.PartyRoleTypesTableFacade;

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

@Named("partyRoleTypesTableController")
@SessionScoped
public class PartyRoleTypesTableController implements Serializable {

    @EJB
    private com.demo.session.PartyRoleTypesTableFacade ejbFacade;
    @EJB
    private com.demo.session.AbstractFacadeQuerySavvy ejbFacade1;
    private List<PartyRoleTypesTable> items = null;
    private List<PartyRoleTypesTable> multiselectionItems = null;
    private List<PartyRoleTypesTable> createItems = null;
    private List<PartyRoleTypesTable> editItems = null;
    private List<PartyRoleTypesTable> filteredValues = null;
    private PartyRoleTypesTable selected;
    private PartyRoleTypesTable selected1;
    private PartyRoleTypesTable selected2 = new PartyRoleTypesTable();
    private String dataName = "PartyRoleTypesTable";
    protected int first;

    public PartyRoleTypesTableController() {
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

    public PartyRoleTypesTable getSelected() {
        return selected;
    }

    public void setSelected(PartyRoleTypesTable selected) {
        this.selected = selected;
    }

    public PartyRoleTypesTable getSelected1() {
        return selected1;
    }

    public void setSelected1(PartyRoleTypesTable selected1) {
        this.selected1 = selected1;
    }

    public PartyRoleTypesTable getSelected2() {
        return selected2;
    }

    public void setSelected2(PartyRoleTypesTable selected2) {
        this.selected2 = selected2;
    }

    public void cancelUpdate() {
        selected1 = null;

        editItems = null;
    }

    public void discard() {
        selected = null;
        for (PartyRoleTypesTable item : getCreateItems()) {
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

    private PartyRoleTypesTableFacade getFacade() {
        return ejbFacade;
    }

    public PartyRoleTypesTable prepareCreate() {
        createItems = new ArrayList<>();

        int tempid = 1;

        selected = new PartyRoleTypesTable();
        selected.setTempId(tempid);

        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public PartyRoleTypesTable prepareCopy() {

        if (createItems == null) {
            createItems = new ArrayList<>();
        }
        selected = multiselectionItems.get(0);
        selected.setId(null);
        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public PartyRoleTypesTable prepareCreateInCreate() {

        selected1 = new PartyRoleTypesTable();
        createItems = preparingTempId(selected1, createItems);
        initializeEmbeddableKey();
        return selected1;
    }

    public PartyRoleTypesTable prepareCreate1() {
        selected = new PartyRoleTypesTable();
        int tempId = 0;
        for (PartyRoleTypesTable p : createItems) {
            if (p.getTempId() > tempId) {
                tempId = p.getTempId();
            }
        }
        selected.setTempId(tempId + 1);
        createItems.add(selected);
        return selected;
    }

    public PartyRoleTypesTable prepareCreateInEdit() {

        selected1 = new PartyRoleTypesTable();
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
        createItems.add(new PartyRoleTypesTable());
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

        for (PartyRoleTypesTable item : getCreateItems()) {
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
        for (PartyRoleTypesTable item : getEditItems()) {
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

        for (PartyRoleTypesTable item : getEditItems()) {
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

    public void removeInCreate(PartyRoleTypesTable item) {

        if (item.getId() == null) {
            createItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            createItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeInEdit(PartyRoleTypesTable item) {

        if (item.getId() == null) {
            editItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            editItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeRecord(PartyRoleTypesTable item) {

        if (item.getId() != null) {
            getFacade().remove(item);
        }

    }

    public void removeList(List<PartyRoleTypesTable> aList) {

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

    public List<PartyRoleTypesTable> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<PartyRoleTypesTable> items) {
        this.items = items;
    }

    public List<PartyRoleTypesTable> getMultiselectionItems() {
        return multiselectionItems;
    }

    public void setMultiselectionItems(List<PartyRoleTypesTable> multiselectionItems) {
        this.multiselectionItems = multiselectionItems;
    }

    public List<PartyRoleTypesTable> getFilteredValues() {
        return filteredValues;
    }

    public void setFilteredValues(List<PartyRoleTypesTable> filteredValues) {
        this.filteredValues = filteredValues;
    }

    public List<PartyRoleTypesTable> getCreateItems() {
        if (createItems == null) {
            createItems = getFacade().findAll();
        }
        return createItems;
    }

    public void setCreateItems(List<PartyRoleTypesTable> createItems) {
        this.createItems = createItems;
    }

    public List<PartyRoleTypesTable> getEditItems() {
        if (editItems == null) {
            editItems = getFacade().findAll();
        }
        return editItems;
    }

    public void setEditItems(List<PartyRoleTypesTable> editItems) {
        this.editItems = editItems;
    }

    private List<PartyRoleTypesTable> preparingTempId(PartyRoleTypesTable item, List<PartyRoleTypesTable> aList) {
        int tempId = 0;
        if (!aList.isEmpty()) {
            for (PartyRoleTypesTable itm : aList) {
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
            for (PartyRoleTypesTable item : multiselectionItems) {
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

    public PartyRoleTypesTable getPartyRoleTypesTable(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PartyRoleTypesTable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PartyRoleTypesTable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PartyRoleTypesTable.class)
    public static class PartyRoleTypesTableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PartyRoleTypesTableController controller = (PartyRoleTypesTableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "partyRoleTypesTableController");
            return controller.getPartyRoleTypesTable(getKey(value));
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
            if (object instanceof PartyRoleTypesTable) {
                PartyRoleTypesTable o = (PartyRoleTypesTable) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PartyRoleTypesTable.class.getName()});
                return null;
            }
        }

    }

}
