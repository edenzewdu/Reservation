package com.demo.jsf;

import com.demo.entity.Book;
import com.demo.jsf.util.JsfUtil;
import com.demo.session.BookFacade;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("bookController")
@SessionScoped
public class BookController implements Serializable {

    @EJB
    private BookFacade bookFacade;

    @Inject
    private ReservationController reservationController;

    private List<Book> items = null;
    private Book selected;

    public BookController() {}

    public Book getSelected() {
        return selected;
    }

    public void setSelected(Book selected) {
        this.selected = selected;
    }

    public List<Book> getItems() {
        if (items == null) {
            items = bookFacade.findAll();
        }
        return items;
    }

    public String prepareEdit(Book book) {

        if (book == null || book.getId() == null) {
            JsfUtil.addErrorMessage("Invalid book selected (no ID).");
            return null;
        }
        if (!reservationController.canEdit("book", book.getId())) {
            JsfUtil.addErrorMessage("This book is already reserved by another user.");
            return null; // stay on the same page
        }

        this.selected = book;
        boolean reserved = reservationController.reserve("book", book.getId());
        if (reserved) {
            return "book"; // or your edit page outcome
        } else {
            selected = null;
            return null;
        }
    }


    public void update() {
        if (selected == null) return;

        if (!reservationController.canEdit("book", selected.getId())) {
            JsfUtil.addErrorMessage("You do not hold the reservation for this record.");
            return;
        }

        try {
            bookFacade.edit(selected);
            JsfUtil.addSuccessMessage("Book updated successfully.");
            reservationController.release("book", selected.getId());
            selected = null;
            items = null;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            JsfUtil.addErrorMessage("Could not update book.");
        }
    }

    public void cancelEdit() {
        if (selected != null) {
            reservationController.release("book", selected.getId());
            selected = null;
        }
    }
    public void reserveIfNotReserved() {
        if (selected != null && selected.getId() != null) {
            reservationController.reserve("book", selected.getId());
        }
    }

    public void refreshActivity() {
        if (selected != null) {
            reservationController.refreshActivity("book", selected.getId());
        }
    }

    public void releaseAndClear() {
        if (selected != null) {
            reservationController.release("book", selected.getId());
            selected = null;
            items = null;
        }
    }

    public boolean isReserved(Book book) {
        return reservationController.isReserved("book", book.getId());
    }

    public String getReservedBy(Book book) {
        return reservationController.getReservedBy("book", book.getId());
    }

}
