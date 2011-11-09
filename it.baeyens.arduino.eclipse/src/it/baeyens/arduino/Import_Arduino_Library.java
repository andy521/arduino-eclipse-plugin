package it.baeyens.arduino;

import it.baeyens.arduino.eclipse.ArduinoHelpers;
import it.baeyens.avreclipse.AVRPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class Import_Arduino_Library implements IImportWizard {

	private Import_Arduino_Library_Page mFolderSelectionPage;
	private IWizardPage[] mPages;
	private IWizardContainer mWizardContainer=null;
	private static String mPageName ="Select";
	private static String mPageTitle = "Select the folder containing the arduino library";
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO validate wether structured selection should not be something else then empty
		//StructuredSelection tt;
		
		mFolderSelectionPage = new Import_Arduino_Library_Page(mPageName,StructuredSelection.EMPTY);
		mFolderSelectionPage.setWizard(this);
		mPages= new IWizardPage[1];
		mPages[0]=mFolderSelectionPage;
	}

	@Override
	public void addPages() {
	}

	@Override
	public boolean canFinish() {
		return mFolderSelectionPage.canFinish();
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
	}

	@Override
	public IWizardContainer getContainer() {
		return mWizardContainer;
	}

	@Override
	public Image getDefaultPageImage() {
		return null;
	}

	@Override
	public IDialogSettings getDialogSettings() {
		return null;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		return null;
	}

	@Override
	public IWizardPage getPage(String pageName) {
		if (mFolderSelectionPage.getName().equals(pageName)) return mFolderSelectionPage;
		return null;
	}

	@Override
	public int getPageCount() {
		return mPages.length;
	}

	@Override
	public IWizardPage[] getPages() {
		return mPages;
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		return null;
	}

	@Override
	public IWizardPage getStartingPage() {
		return mPages[0];
	}

	@Override
	public RGB getTitleBarColor() {
		return null;
	}

	@Override
	public String getWindowTitle() {
		return mPageTitle;
	}

	@Override
	public boolean isHelpAvailable() {
		return false;
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return false;
	}

	@Override
	public boolean needsProgressMonitor() {
		return false;
	}

	@Override
	public boolean performCancel() {
		return true;
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		//mFolderSelectionPage.getFolderProvider().;
		try {
			ArduinoHelpers.addCodeFolder( mFolderSelectionPage.GetProject(), new Path(mFolderSelectionPage.GetLibraryFolder()));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			IStatus status = new Status(Status.ERROR, ArduinoConst.CORE_PLUGIN_ID,	"Failed to import library " , e);
			AVRPlugin.getDefault().log(status);
			return false;
		}
		return true;
	}

	@Override
	public void setContainer(IWizardContainer wizardContainer) {
		mWizardContainer = wizardContainer;
	}

}
