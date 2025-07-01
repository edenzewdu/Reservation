package com.demo.jsf;

import com.demo.entity.MilestoneStatusesTable;
import com.demo.jsf.util.JsfUtil;
import com.demo.jsf.util.JsfUtil.PersistAction;
import com.demo.session.MilestoneStatusesTableFacade;

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

@Named("milestoneStatusesTableController")
@SessionScoped
public class MilestoneStatusesTableController implements Serializable {

    @EJB
    private com.demo.session.MilestoneStatusesTableFacade ejbFacade;
    @EJB
    private com.demo.session.AbstractFacadeQuerySavvy ejbFacade1;
    private List<MilestoneStatusesTable> items = null;
    private List<MilestoneStatusesTable> multiselectionItems = null;
    private List<MilestoneStatusesTable> createItems = null;
    private List<MilestoneStatusesTable> editItems = null;
    private List<MilestoneStatusesTable> filteredValues = null;
    private MilestoneStatusesTable selected;
    private MilestoneStatusesTable selected1;
    private MilestoneStatusesTable selected2 = new MilestoneStatusesTable();
    private String dataName = "MilestoneStatusesTable";
    protected int first;

    public MilestoneStatusesTableController() {
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

    public MilestoneStatusesTable getSelected() {
        return selected;
    }

    public void setSelected(MilestoneStatusesTable selected) {
        this.selected = selected;
    }

    public MilestoneStatusesTable getSelected1() {
        return selected1;
    }

    public void setSelected1(MilestoneStatusesTable selected1) {
        this.selected1 = selected1;
    }

    public MilestoneStatusesTable getSelected2() {
        return selected2;
    }

    public void setSelected2(MilestoneStatusesTable selected2) {
        this.selected2 = selected2;
    }

    public void cancelUpdate() {
        selected1 = null;

        editItems = null;
    }

    public void discard() {
        selected = null;
        for (MilestoneStatusesTable item : getCreateItems()) {
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

    private MilestoneStatusesTableFacade getFacade() {
        return ejbFacade;
    }

    public MilestoneStatusesTable prepareCreate() {
        createItems = new ArrayList<>();

        int tempid = 1;

        selected = new MilestoneStatusesTable();
        selected.setTempId(tempid);

        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public MilestoneStatusesTable prepareCopy() {

        if (createItems == null) {
            createItems = new ArrayList<>();
        }
        selected = multiselectionItems.get(0);
        selected.setId(null);
        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public MilestoneStatusesTable prepareCreateInCreate() {

        selected1 = new MilestoneStatusesTable();
        createItems = preparingTempId(selected1, createItems);
        initializeEmbeddableKey();
        return selected1;
    }

    public MilestoneStatusesTable prepareCreate1() {
        selected = new MilestoneStatusesTable();
        int tempId = 0;
        for (MilestoneStatusesTable p : createItems) {
            if (p.getTempId() > tempId) {
                tempId = p.getTempId();
            }
        }
        selected.setTempId(tempId + 1);
        createItems.add(selected);
        return selected;
    }

    public MilestoneStatusesTable prepareCreateInEdit() {

        selected1 = new MilestoneStatusesTable();
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
        createItems.add(new MilestoneStatusesTable());
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

        for (MilestoneStatusesTable item : getCreateItems()) {
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
        for (MilestoneStatusesTable item : getEditItems()) {
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

        for (MilestoneStatusesTable item : getEditItems()) {
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

    public void removeInCreate(MilestoneStatusesTable item) {

        if (item.getId() == null) {
            createItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            createItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeInEdit(MilestoneStatusesTable item) {

        if (item.getId() == null) {
            editItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            editItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeRecord(MilestoneStatusesTable item) {

        if (item.getId() != null) {
            getFacade().remove(item);
        }

    }

    public void removeList(List<MilestoneStatusesTable> aList) {

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

    public List<MilestoneStatusesTable> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<MilestoneStatusesTable> items) {
        this.items = items;
    }

    public List<MilestoneStatusesTable> getMultiselectionItems() {
        return multiselectionItems;
    }

    public void setMultiselectionItems(List<MilestoneStatusesTable> multiselectionItems) {
        this.multiselectionItems = multiselectionItems;
    }

    public List<MilestoneStatusesTable> getFilteredValues() {
        return filteredValues;
    }

    public void setFilteredValues(List<MilestoneStatusesTable> filteredValues) {
        this.filteredValues = filteredValues;
    }

    public List<MilestoneStatusesTable> getCreateItems() {
        if (createItems == null) {
            createItems = getFacade().findAll();
        }
        return createItems;
    }

    public void setCreateItems(List<MilestoneStatusesTable> createItems) {
        this.createItems = createItems;
    }

    public List<MilestoneStatusesTable> getEditItems() {
        if (editItems == null) {
            editItems = getFacade().findAll();
        }
        return editItems;
    }

    public void setEditItems(List<MilestoneStatusesTable> editItems) {
        this.editItems = editItems;
    }

    private List<MilestoneStatusesTable> preparingTempId(MilestoneStatusesTable item, List<MilestoneStatusesTable> aList) {
        int tempId = 0;
        if (!aList.isEmpty()) {
            for (MilestoneStatusesTable itm : aList) {
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
            for (MilestoneStatusesTable item : multiselectionItems) {
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

    public MilestoneStatusesTable getMilestoneStatusesTable(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MilestoneStatusesTable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MilestoneStatusesTable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MilestoneStatusesTable.class)
    public static class MilestoneStatusesTableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MilestoneStatusesTableController controller = (MilestoneStatusesTableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "milestoneStatusesTableController");
            return controller.getMilestoneStatusesTable(getKey(value));
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
            if (object instanceof MilestoneStatusesTable) {
                MilestoneStatusesTable o = (MilestoneStatusesTable) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MilestoneStatusesTable.class.getName()});
                return null;
            }
        }

    }

}
