package ChessAPI;

public class ApiVersion {
	private static String versionNo = "1.0";

	public static String getVersionNo()
	{
		//return the version
		return versionNo;
	}

	public static void printVersionNo()
	{
		System.out.println("Current VersionNo. is:" + versionNo);
	}
	
	

}
