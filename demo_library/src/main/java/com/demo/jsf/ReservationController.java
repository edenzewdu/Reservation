package com.demo.jsf;

import com.demo.entity.Book;
import com.demo.entity.Reservation;
import com.demo.entity.SystemConfiguration;
import com.demo.jsf.util.JsfUtil;
import com.demo.jsf.util.JsfUtil.PersistAction;
import com.demo.session.BookFacade;
import com.demo.session.ReservationFacade;

import com.demo.session.SystemConfigurationFacade;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.ejb.Schedule;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Named("reservationController")
@SessionScoped
public class ReservationController implements Serializable {

    @Inject
    private BookFacade bookFacade;
    @EJB
    private ReservationFacade reservationFacade;

    @EJB
    private SystemConfigurationFacade configFacade;


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
        if (!"book".equals(tableName)) {
            // For now, only book supports reserved flag update
            return reserveGeneric(tableName, rowId);
        }

        Reservation existing = reservationFacade.findActiveReservation(tableName, rowId);

        if (existing == null) {
            Book book = bookFacade.find(rowId);
            bookFacade.edit(book);

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
        return res == null || res.getReservedBy().equals(currentUser);
    }

    public String getReservedBy(String tableName, Integer rowId) {
        Reservation res = reservationFacade.findActiveReservation(tableName, rowId);
        return res != null ? res.getReservedBy() : null;
    }

    private boolean reserveGeneric(String tableName, Integer rowId) {
        // Your existing logic without book reserved flag update
        // ...
        return false; // placeholder
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
    @Schedule(hour = "*", minute = "*", second = "0", persistent = false)
    public void cleanupExpiredReservations() {
        SystemConfiguration config = configFacade.getConfig();
        int fixedMinutes = config != null ? config.getFixedDuration() : 10;
        Date expiredBefore = new Date(System.currentTimeMillis() - fixedMinutes * 60 * 1000L);

        List<Reservation> expired = reservationFacade.findExpiredReservations(expiredBefore);
        for (Reservation res : expired) {
            if ("book".equals(res.getReservedTable())) {
                Book book = bookFacade.find(res.getRowId());
                    bookFacade.edit(book);
            }
            reservationFacade.remove(res);
        }
    }

    public void refreshActivity(String table, Integer rowId) {
        Reservation res = reservationFacade.findActiveReservation(table, rowId);
        if (res != null && res.getReservedBy().equals(currentUser)) {
            res.setLastActivityTime(new Date());
            reservationFacade.edit(res);
        }
    }

    @Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
    public void cleanupStaleReservations() {
        SystemConfiguration config = configFacade.getConfig();
        int inactivityMinutes = config != null ? config.getNoActivityForReserved() : 5;
        Date staleBefore = new Date(System.currentTimeMillis() - inactivityMinutes * 60 * 1000L);


        List<Reservation> staleReservations = reservationFacade.findInactiveSince(staleBefore);

        for (Reservation r : staleReservations) {
            if ("book".equals(r.getReservedTable())) {
                Book book = bookFacade.find(r.getRowId());
                    bookFacade.edit(book);
            }
            reservationFacade.remove(r);
        }
    }


}
