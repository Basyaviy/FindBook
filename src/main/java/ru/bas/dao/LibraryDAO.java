package ru.bas.dao;

import java.util.List;

import ru.bas.entity.Library;

public interface LibraryDAO {
	
	public List<Library> getLibraries();

	public void saveLibrary(Library theLibrary);

	public void deleteLibrary(int theId);

}
