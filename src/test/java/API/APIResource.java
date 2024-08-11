package API;

public enum APIResource {
	
	addBookAPI("Library/Addbook.php"),
	getBookAPI("/Library/GetBook.php"),
	deleteBookAPI("/Library/DeleteBook.php");
	private String resource;

	APIResource(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	
	public String getAPIResource()
	{
		return resource;
	}

}
