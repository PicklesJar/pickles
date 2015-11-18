package picklesjar.pickles.ut.prepare.design;

public enum StereotypeDesign
	implements Design {
	
	NOT_STEREOTYPE,
	ACCESSOR_GETTER( "POJO/Accessor", "@ACCESSOR_GETTER" ),
	ACCESSOR_SETTER( "POJO/Accessor", "@ACCESSOR_SETTER" );
	
	private String root = null;
	
	private String tag = null;
	
	private StereotypeDesign() {
	
	}
	
	private StereotypeDesign( String root, String tag ) {
	
		if( ( root == null ) || ( root.isEmpty() ) ) {
			throw new IllegalStateException();
		}
		
		this.root = root;
		this.tag = tag;
	}
	
	public String getRoot() {
	
		return root;
	}
	
	public String getTag() {
	
		return tag;
	}
}
