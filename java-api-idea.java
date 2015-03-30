import org.bh.tools.net.barc.*;
/*

BarcDelegate        - An implementation of the BARC protocol (interface)
	MyBarcDelegateImpl is only used in this example. It theoretically implements all methods in BarcDelegate.
BarcUpdateListener  - A listener with methods that are called throughout the process, for multithreading efficiency
BarcData            - A basic structure for data about a version of the program (interface)
	BarcLocalData   - An implementation of BarcData for the local version of the program
	BarcRemoteData  - An implementation of BarcData for a remote version of the program
BarcVersion         - A data structure that holds the version number. Implements Comparable.
BarcUpdate          - Contains data about an available update
BarcChangelog       - Contains the changelog about an available update. Implements Iterable.
BarcChange          - A single change about the current update
	BarcChange.Type - An enum representing the type of change
BarcProgram         - Represents a program that implements the BARC protocol. Extends File.
BarcSandbox         - Represents a single, local installation of a version of the Program. Extends File. Should be
                      located within a BarcProgram.

*/

public class MyBarcConnector {
	public static void main(String[] args) {
		BarcDelegate delegate = new MyBarcDelegateImpl();
		BarcUpdateListener updateListener = new MyBarcUpdateListener();
		delegate.setUpdateListener(updateListener);
		
		BarcLocalData localData = delegate.getLocalData();
		BarcVersion localVersion = localData.getVersion();
		BarcRemoteData remoteData = delegate.fetchRemoteDataSinceVersion(localVersion); // lengthy operation, this thread
		
		BarcVersion remoteVersion = remoteData.getVersion();
		
		boolean needsUpdate = localVersion.compareTo(remoteVersion) < 0;
		
		if (needsUpdate) {
			BarcUpdate update = remoteData.getUpdateData();
			BarcChangelog changes = update.getChanges();
			
			for(BarcChange change : changes) {
				BarcChange change = changes.getChange(0);
				BarcChange.Type changeType = change.getType();
				boolean changeIsImportant = changeType == BarcChange.Type.IMPORTANT;
				
				System.out.println((changeIsImportant ? "!! Important Update !! " : "Update: ") + change.getText());
			}
			
			BarcProgram program = new MyProgram();
			delegate.update(program); // lengthy operation, separate thread
		}
	}
}