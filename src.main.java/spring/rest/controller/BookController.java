package spring.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.model.BookModel;
import spring.rest.service.BookService;

@RestController
@RequestMapping(value = "books")
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public List<BookModel> getBooks() {
		return this.bookService.getAllBook();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BookModel> addBook(@RequestBody BookModel bookModel) {
//		 bookModel=new BookModel(9,"java","ab",111);
		System.out.println("value"+bookModel);
		System.out.println(bookModel.getAuther_name());
		bookService.addBook(bookModel);
		return new ResponseEntity<BookModel>(bookModel, HttpStatus.CREATED);
//		return "Redirect:/BookController/book";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<BookModel> edit(@PathVariable("id") int id, @RequestBody BookModel bookModel) {
		System.out.println("Request user id is " + id);
		bookModel.setId(id);
		bookService.editBook(bookModel);
//		ErrorCode.ERROR_VALIDATION.getCode();
		return new ResponseEntity<BookModel>(bookModel, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") int id) {
		bookService.deleteBook(id);
		return "delete successfully";
	}
	
	

}
