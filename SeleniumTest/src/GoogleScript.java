
public class GoogleScript {
	public static void main(String[] args) {
		GooglePage objGooglePage = new GooglePage();
		objGooglePage.openGoogle();
		objGooglePage.searchText();
		objGooglePage.openParticularPage();
		objGooglePage.clickOnSecondLastLink();
		objGooglePage.getTitlePage();
	}

}
