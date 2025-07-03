package com.demo.jsf;

import com.demo.entity.Book;
import com.demo.jsf.util.JsfUtil;
import com.demo.session.BookFacade;
import com.demo.session.ReservationFacade;
import com.demo.entity.Reservation;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

@Named("bookController")
@SessionScoped
public class BookController implements Serializable {

    @EJB
    private BookFacade bookFacade;

    @EJB
    private ReservationFacade reservationFacade;

    private List<Book> books;
    private Book selected;

    private String currentUser = "userA"; // Replace with real session user

    public List<Book> getBooks() {
        if (books == null) {
            books = bookFacade.findAll();
        }
        return books;
    }

    public Book getSelected() {
        return selected;
    }

    public void setSelected(Book selected) {
        this.selected = selected;
    }

    public void autoReserve() {
        if (selected == null) return;

        Reservation existing = reservationFacade.findActiveReservation("book", selected.getId());
        if (existing == null) {
            Reservation r = new Reservation();
            r.setReservedTable("book");
            r.setRowId(selected.getId());
            r.setReservedBy(currentUser);
            r.setReservedStartTime(new Date());
            reservationFacade.create(r);
            JsfUtil.addSuccessMessage("Record reserved.");
        } else if (!existing.getReservedBy().equals(currentUser)) {
            JsfUtil.addErrorMessage("This book is currently reserved by " + existing.getReservedBy());
            selected = null; // Unselect
        }
    }

    public void save() {
        bookFacade.edit(selected);
        Reservation r = reservationFacade.findActiveReservation("book", selected.getId());
        if (r != null) reservationFacade.remove(r); // release
        selected = null;
        books = null;
        JsfUtil.addSuccessMessage("Book updated and reservation released.");
    }
}
