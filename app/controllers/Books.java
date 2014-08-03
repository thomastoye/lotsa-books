package controllers;

import models.Book;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Books extends Controller {
	private static final Form<Book> bookForm = new Form<>(Book.class);	
	
	/**
	 * List all books
	 * @return
	 */
	public static Result list() {
		return ok(list.render(Book.findAll()));
	}
	
	/**
	 * List details for a given book
	 * @param id The book
	 * @return
	 */
	public static Result details(long id) {
		return TODO;
	}
	
	/**
	 * Show form to add a new Book
	 * @return
	 */
	public static Result newBook() {
		return ok(views.html.bookForm.render(bookForm));
	}
	
	/**
	 * Show filled form to edit an existing book
	 * @param id The id of the Book
	 * @return
	 */
	public static Result edit(long id) {
		return TODO;
	}
	
	/**
	 * Save a book by binding a request
	 * @return
	 */
	public static Result save() {
		Form<Book> boundForm = bookForm.bindFromRequest();
		
		if(boundForm.hasErrors()) {
			flash("There was an error in you submission. Please try again.");
			return badRequest(views.html.bookForm.render(boundForm));
		}
		
		Book boundBook = boundForm.get();
		if(boundBook.id == null)
			boundBook.save();
		else
			boundBook.update();
		
		return redirect(routes.Books.details(boundBook.id));
	}
}
