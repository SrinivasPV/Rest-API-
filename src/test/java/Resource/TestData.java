package Resource;

import Pojo.AddBook;
import Pojo.DeleteBook;

public class TestData {
	

	public AddBook addBookBody()
	{
		AddBook ab = new AddBook();
		ab.setName("Indian 3.0");
		ab.setIsbn("asf");
		ab.setAisle("704");
		ab.setAuthor("John foe");
		return ab;
	}
	
	public DeleteBook deleteBookBody(String id)
	{
		DeleteBook d = new DeleteBook();
		d.setId(id);
		return d;
	}

}
