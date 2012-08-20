package org.mapa.test;

import org.mapa.R;

import com.Salsalectric.main.MainActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

public class TodoElementListFragment extends
		ActivityInstrumentationTestCase2<MainActivity>{
	
	private Solo solo;
	
	public TodoElementListFragment() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testForTodoElementList()
	{
		View v = solo.getView(R.id.TodoList);
		assertNotNull( "No Todo Element list!" , v);
	}

	public void testForNewElement()
	{
		assertTrue( "Add new Element List Entry not found!" , solo.searchText(getActivity().getString(R.string.AddNewElement)) );
	}
	
	//Test if it is possible to add a new element and set its name
	public void testEditingElement()
	{		
		//Make sure there is no empty element
		assertFalse( "There should not be an empty element in the list!" , solo.searchText(getActivity().getString(R.string.todoElement_name_hint), 1, false, true));
		solo.clickOnText(getActivity().getString(R.string.AddNewElement));
		assertTrue( "Adding new element failed!" , solo.searchText(getActivity().getString(R.string.todoElement_name_hint)) );
		solo.clickOnText(getActivity().getString(R.string.todoElement_name_hint));
		View v = solo.getView(R.id.TodoEditFragment);
		assertNotNull("No TodoEditFragment", v);
		assertTrue( "TodoEditFragment not visible." , v.isShown() );
		EditText  e = (EditText) solo.getView(R.id.name);
		solo.typeText(e, "TestName" );
		solo.clickOnView(solo.getView(R.id.button_acceptChanges));
		//Now check if new elment is part of the list
		assertTrue( "Creating new Element failed!" , solo.searchText("TestName") );
	}
}

