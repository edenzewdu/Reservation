package com.demo.jsf;

import com.demo.entity.Penalties;
import com.demo.jsf.util.JsfUtil;
import com.demo.jsf.util.JsfUtil.PersistAction;
import com.demo.session.PenaltiesFacade;

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

@Named("penaltiesController")
@SessionScoped
public class PenaltiesController implements Serializable {

    @EJB
    private com.demo.session.PenaltiesFacade ejbFacade;
    @EJB
    private com.demo.session.AbstractFacadeQuerySavvy ejbFacade1;
    private List<Penalties> items = null;
    private List<Penalties> multiselectionItems = null;
    private List<Penalties> createItems = null;
    private List<Penalties> editItems = null;
    private List<Penalties> filteredValues = null;
    private Penalties selected;
    private Penalties selected1;
    private Penalties selected2 = new Penalties();
    private String dataName = "Penalties";
    protected int first;

    public PenaltiesController() {
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

    public Penalties getSelected() {
        return selected;
    }

    public void setSelected(Penalties selected) {
        this.selected = selected;
    }

    public Penalties getSelected1() {
        return selected1;
    }

    public void setSelected1(Penalties selected1) {
        this.selected1 = selected1;
    }

    public Penalties getSelected2() {
        return selected2;
    }

    public void setSelected2(Penalties selected2) {
        this.selected2 = selected2;
    }

    public void cancelUpdate() {
        selected1 = null;

        editItems = null;
    }

    public void discard() {
        selected = null;
        for (Penalties item : getCreateItems()) {
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

    private PenaltiesFacade getFacade() {
        return ejbFacade;
    }

    public Penalties prepareCreate() {
        createItems = new ArrayList<>();

        int tempid = 1;

        selected = new Penalties();
        selected.setTempId(tempid);

        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public Penalties prepareCopy() {

        if (createItems == null) {
            createItems = new ArrayList<>();
        }
        selected = multiselectionItems.get(0);
        selected.setId(null);
        createItems.add(selected);
        initializeEmbeddableKey();
        return selected;
    }

    public Penalties prepareCreateInCreate() {

        selected1 = new Penalties();
        createItems = preparingTempId(selected1, createItems);
        initializeEmbeddableKey();
        return selected1;
    }

    public Penalties prepareCreate1() {
        selected = new Penalties();
        int tempId = 0;
        for (Penalties p : createItems) {
            if (p.getTempId() > tempId) {
                tempId = p.getTempId();
            }
        }
        selected.setTempId(tempId + 1);
        createItems.add(selected);
        return selected;
    }

    public Penalties prepareCreateInEdit() {

        selected1 = new Penalties();
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
        createItems.add(new Penalties());
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

        for (Penalties item : getCreateItems()) {
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
        for (Penalties item : getEditItems()) {
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

        for (Penalties item : getEditItems()) {
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

    public void removeInCreate(Penalties item) {

        if (item.getId() == null) {
            createItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            createItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeInEdit(Penalties item) {

        if (item.getId() == null) {
            editItems.removeIf(eachElement -> eachElement.getTempId().equals(item.getTempId()));
        } else {
            editItems.removeIf(eachElement -> eachElement.getId().equals(item.getId()));
            getFacade().remove(item);
        }

    }

    public void removeRecord(Penalties item) {

        if (item.getId() != null) {
            getFacade().remove(item);
        }

    }

    public void removeList(List<Penalties> aList) {

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

    public List<Penalties> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<Penalties> items) {
        this.items = items;
    }

    public List<Penalties> getMultiselectionItems() {
        return multiselectionItems;
    }

    public void setMultiselectionItems(List<Penalties> multiselectionItems) {
        this.multiselectionItems = multiselectionItems;
    }

    public List<Penalties> getFilteredValues() {
        return filteredValues;
    }

    public void setFilteredValues(List<Penalties> filteredValues) {
        this.filteredValues = filteredValues;
    }

    public List<Penalties> getCreateItems() {
        if (createItems == null) {
            createItems = getFacade().findAll();
        }
        return createItems;
    }

    public void setCreateItems(List<Penalties> createItems) {
        this.createItems = createItems;
    }

    public List<Penalties> getEditItems() {
        if (editItems == null) {
            editItems = getFacade().findAll();
        }
        return editItems;
    }

    public void setEditItems(List<Penalties> editItems) {
        this.editItems = editItems;
    }

    private List<Penalties> preparingTempId(Penalties item, List<Penalties> aList) {
        int tempId = 0;
        if (!aList.isEmpty()) {
            for (Penalties itm : aList) {
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
            for (Penalties item : multiselectionItems) {
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

    public Penalties getPenalties(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Penalties> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Penalties> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Penalties.class)
    public static class PenaltiesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PenaltiesController controller = (PenaltiesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "penaltiesController");
            return controller.getPenalties(getKey(value));
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
            if (object instanceof Penalties) {
                Penalties o = (Penalties) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Penalties.class.getName()});
                return null;
            }
        }

    }

}
