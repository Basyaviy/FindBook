package ru.bas.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="library")
public class Library {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="path")
	private String path;
	
	//deleting library delete all referenced books
	@OneToMany( mappedBy="library", cascade = {CascadeType.ALL})
	private List<Book> books;

	public Library() {}

	public Library(String path) {
		this.path = path;
	}
	
	public void add(Book tempBook) {
		if(books == null) {
			books = new ArrayList<>();
		}
		books.add(tempBook);
		tempBook.setLibrary(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Library [id=" + id + ", path=" + path + ", books=" + books + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(books, id, path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Library other = (Library) obj;
		return Objects.equals(path, other.path);
	}
	
	


	
	
	
	
	

}
