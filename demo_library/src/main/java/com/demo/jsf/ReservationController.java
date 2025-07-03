package com.demo.jsf;

import com.demo.entity.Reservation;
import com.demo.jsf.util.JsfUtil;
import com.demo.jsf.util.JsfUtil.PersistAction;
import com.demo.session.ReservationFacade;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Named("reservationController")
@SessionScoped
public class ReservationController implements Serializable {

    @EJB
    private ReservationFacade reservationFacade;

    private List<Reservation> items = null;

    // Simulated current user (replace with real login system)
    private final String currentUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() != null ?
            FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() : "User-" + System.currentTimeMillis();

    public ReservationController() {}

    public List<Reservation> getItems() {
        if (items == null) {
            items = reservationFacade.findAll();
        }
        return items;
    }

    // ----------- Generic Reservation Methods ------------ //

    public boolean reserve(String tableName, Integer rowId) {
        Reservation existing = reservationFacade.findActiveReservation(tableName, rowId);

        if (existing == null) {
            Reservation res = new Reservation();
            res.setReservedTable(tableName);
            res.setRowId(rowId);
            res.setReservedBy(currentUser);
            res.setReservedStartTime(new Date());
            reservationFacade.create(res);
            JsfUtil.addSuccessMessage("Reserved successfully.");
            return true;
        } else if (existing.getReservedBy().equals(currentUser)) {
            // already reserved by same user
            return true;
        } else {
            JsfUtil.addErrorMessage("Already reserved by " + existing.getReservedBy());
            return false;
        }
    }

    public void release(String tableName, Integer rowId) {
        Reservation existing = reservationFacade.findActiveReservation(tableName, rowId);
        if (existing != null && existing.getReservedBy().equals(currentUser)) {
            reservationFacade.remove(existing);
            JsfUtil.addSuccessMessage("Reservation released.");
        }
    }

    public boolean isReserved(String tableName, Integer rowId) {
        return reservationFacade.findActiveReservation(tableName, rowId) != null;
    }

    public boolean canEdit(String tableName, Integer rowId) {
        Reservation res = reservationFacade.findActiveReservation(tableName, rowId);
        return res != null && res.getReservedBy().equals(currentUser);
    }

    public String getReservedBy(String tableName, Integer rowId) {
        Reservation res = reservationFacade.findActiveReservation(tableName, rowId);
        return res != null ? res.getReservedBy() : null;
    }

    // Optional: auto release on logout/session timeout
    public void releaseAllByCurrentUser() {
        List<Reservation> reservedByUser = reservationFacade.findByReservedBy(currentUser);
        for (Reservation r : reservedByUser) {
            reservationFacade.remove(r);
        }
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
